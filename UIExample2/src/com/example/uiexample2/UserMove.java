package com.example.uiexample2;

import com.example.uiexample2.R;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;



public class UserMove extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 전체 화면 사용하기		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		// MapMove 호출
		setContentView(new MapMove(this, null));
	}


	class UserView extends View {
		Bitmap user;				// 캐릭터의 비트맵 이미지
    	
    	int cw, ch;					// 캐릭터의 폭과 높이
    	int x = 100, y = 100;		// 캐릭터의 현재 좌표
    	int dx = 3, dy = 3;			// 캐릭터가 이동할 방향과 거리
    	
    	int width, height;			// 화면의 폭과 높이
    	
		public UserView(Context context) {	// 초기화
			super(context);
			
			// 해상도 구하기
			Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			width = display.getWidth();		// 화면의 폭
			height = display.getHeight();	// 화면의 높이
			
			user = BitmapFactory.decodeResource(context.getResources(), R.drawable.user);	// 사용자 이미지
			cw = user.getWidth()/2;		// 이미지 폭의 중간
			ch = user.getHeight()/2;	// 이미지 높이의 중간
			
			Timer.sendEmptyMessageDelayed(0, 10);	// 핸들러는 스스로 실행되지 않으므로 핸들러의 외부에서 한 번 실행시켜주어야 한다
		}
    	
		public void onDraw(Canvas canvas) {
			//x += dx;			// 가로 방향으로 이동
			//y += dy;			// 세로 방향으로 이동
			
			if(x < cw){			// 왼쪽 끝이면 방향을 바꿈
				x = cw;
				//dx = -dx;		
			}
			if(x > width-cw){	// 오른쪽 끝이면 방향을 바꿈
				x = width - cw;
				//dx = -dx;
			}
			if(y < ch){			// 위 끝이면 방향을 바꿈
				y = ch;
				//dy = -dy;
			}
			if(y > height-ch){	// 아래 끝이면 방향을 바꿈
				y = height-ch;
				//dy = -dy;
			}
			canvas.drawBitmap(user, x-cw, y-ch, null);	// 사용자 이미지 붙이기
		}
		
		Handler Timer = new Handler(){					// onDraw()를 일정한 간격으로 실행시켜 주는 타이머 핸들러
			public void handleMessage(Message msg){
				invalidate();								// 스레드 처리 중 시간이 나면 onDraw() 다시 실행
				Timer.sendEmptyMessageDelayed(0, 10);	//10/1000초마다 실행
			}
		};
    }
}
