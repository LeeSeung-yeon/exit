/*
 * !!!!!!!!!!! Have To Do !!!!!!!!!!!!!
 * 
 * 타이틀과 StatusBar 등등 고려해서 높이 조정 해야함
 * 
 */



package com.example.uiexample2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import android.view.SurfaceHolder.Callback;

public class MapMove extends SurfaceView implements Callback {
	/*
	 *  스레드는 혼자서 동작하는 것이 아니고 SurfaceView에서 호출해 줘야 
	 *  실행(SurfaceHolder를 이용해서 그림을 그리고, 비트맵 이미지를 읽음)한다.
	 *  이 때 Context가 필요하므로 SurfaceView로부터 이와 관련된 자료를 함수의 호출인자로 넘겨받던지 
	 *  전역변수로 만들어 서로 공유하도록 해야한다.
	 */
	private Context			mContext;
	private SurfaceHolder	mHolder;
	private MapThread 		mThread;
	
	private int width, height, cx, cy;	// 화면의 폭과 중심점
	private int x1, y1;
	private int x2, y2;					// Viewport 시작점
	private int sx1, sy1, sx2, sy2;		// Viewport가 스크롤되는 속도
	private Rect src, dst;				// Viewport와 View 설정용
	private Bitmap pink;				// 배경화면
	private Bitmap user;				// 사용자 이미지
	private int w, h;					// 사용자 이미지의 폭과 높이
	private long counter = 0;			// 전체 반복 횟수
	private boolean canRun = true;		// 스레드 실행용 플래그
	
	//Paint paint;
	//MapPath mp;
	
	public MapMove(Context context, AttributeSet attrs) {	// 생성자
		super(context, attrs);
		
		SurfaceHolder holder = getHolder();	// SurfaceView를 직접 다루는 SurfaceHolder
		holder.addCallback(this);			// Collback 함수 등록
		
		mContext = context;			// 인수로 넘어 온 context를 전역변수에 저장
		mHolder = holder;			// 생성한 holder를 전역변수에 저장
		mThread = new MapThread();	// MoveThread 생성
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {		// SurfaceView가 만들어질 때 호출됨
		mThread.start();	// thread를 동작시킨다 이 때 run() 메소드가 실행된다
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// SurfaceView의 크기가 바뀔 때 호출됨

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {	// SurfaceView가 종료될 때 호출됨
		boolean done = true;	// 종료 플래그
		while (done) {
			try{
				mThread.join();	// 스레드가 현재 step을 끝낼 떄까지 대기
				done = false;
			} catch (InterruptedException e) {
				// 인터럽트 발생 시 아무것도 안함
			}
		}

	}

	class MapThread extends Thread {	// SurfaceHolder를 움직일 스레드
		
		public MapThread() {
			Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			width = display.getWidth();		// 화면의 폭
			height = display.getHeight();	// 화면의 높이
			// 화면의 중심
			cx = width / 2;		// 화면 폭의 중간
			cy = height / 2;	// 화면 높이의 중간
			
			
			// 배경과 사용자 이미지 읽어옴
			pink = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.smash_room);
			pink = Bitmap.createScaledBitmap(pink, width, height, true);		// 이미지 확대
			//pink = Bitmap.createScaledBitmap(pink, width, height, true);
			
			user = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.user);
			// 사용자 이미지의 중심
			w = user.getWidth() / 2;	// 사용자 이미지 폭의 중간
			h = user.getHeight() / 2;	// 사용자 이미지 높이의 중간
			
			dst = new Rect(0, 0, width, Integer.parseInt(String.valueOf(Math.round(height*0.8))));	// View의 크기
			src = new Rect();						// Viewport용
			
			// Viewport의 시작위치
			x1 = pink.getWidth() - width;	
			y1 = pink.getHeight() - height;
			
			// Viewport를 1회에 이동시킬 거리
			sx1 = 0;
			sy1 = 0;
		}
		
		public void run() {		// 실제로 반복되는 부분
			Canvas canvas = null;								// canvas를 만든다
			
			while (canRun == true) {
				canvas = mHolder.lockCanvas();					// canvas를 잠그고 버퍼 할당
				try {
					synchronized (mHolder) {					// 동기화 유지
						//paint = new Paint(256);
						//mp = new MapPath();
						//Drawing(canvas);
						ScrollViewport();						// 배경화면 스크롤
						//src.set(x1, y1, x1+width, y1+height);	// Viewport 설정
						canvas.drawBitmap(pink, src, dst, null);	// 배경화면 그리기
						canvas.drawBitmap(user, cx-w, cy-h, null);	// 사용자 이미지 그리기
					}
				} finally {
					mHolder.unlockCanvasAndPost(canvas);		// canvas의 내용을 View에 전송
				}
			}
		}
	}

	public void ScrollViewport() {
		// 사용자가 화면 밖으로 나갔을 때 지도 움직임
		
		
		
		counter++;
		if (counter%2 == 0) {	// 스크롤 속도를 늦추기 위해 루프의 2회에 1번씩 스크롤한다
			// Viewport를 위로이동 (sx는 음수임)
			x1 += sx1;
			y1 += sy1;
			
			// Viewport를 벗어났을 때
			if (x1 < 0)	x1 = pink.getWidth() - width;
			if (y1 < 0)	x1 = pink.getHeight() - height;
			
			src.set(x1, y1, x1+width, y1+height);	// Viewport 설정
		}
	}
	/*
	public void Drawing(Canvas c){
		for(int a=0;a<=mp.a;a++){
			c.drawRect(mp.yy[a], mp.xx[a], mp.yy[a]+5, mp.xx[a]+5, paint);
		}
	}*/	
}
