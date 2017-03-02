package in.hocg.wallpaper.service;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import in.hocg.wallpaper.Utils;

/**
 * Created by hocgin on 01/03/2017.
 */

public class ScreenService extends WallpaperService {

	@Override
	public Engine onCreateEngine() {
		return new ScreenEngine();
	}


	// 壁纸绘制
	class ScreenEngine extends Engine {
		Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		// 计算位置
		int width = display.getWidth();
		int height = display.getHeight();

		// 记录程序界面是否可见
		private boolean isVisible;
		// 定义画笔
		private Paint paint = new Paint();

		// 定义一个Handler
		Handler handler = new Handler();

		// 定义一个周期性执行的任务
		private final Runnable drawRunnable = new Runnable() {
			public void run() {
				// 动态地绘制图形
				drawScreen();
			}
		};

		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {
			super.onCreate(surfaceHolder);
			// 初始化画笔
			paint.setAntiAlias(true);
			paint.setStrokeWidth(2);
			paint.setStrokeCap(Paint.Cap.ROUND);
			paint.setStyle(Paint.Style.FILL);
			paint.setTextAlign(Paint.Align.CENTER);
			paint.setTypeface(Typeface.DEFAULT);
			// 开启处理触摸事件
//			setTouchEventsEnabled(true);
		}

		@Override
		public void onVisibilityChanged(boolean visible) {

			// 当界面可见时候，执行drawFrame()方法。
			if (isVisible = visible) {
				// 动态地绘制图形
				drawScreen();
			} else {
				// 如果界面不可见，删除回调
				handler.removeCallbacks(drawRunnable);
			}
		}


		/**
		 * 绘制图片
		 */
		private void drawScreen(){
			final SurfaceHolder holder = getSurfaceHolder();
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas();
				if (canvas != null) {
					// 获取时间, 计算演示
					String colorString = String.format("%s", Utils.time2color());
					int color = Color.parseColor("#ff" + colorString);

					// 清除绘制
					canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC);
					// 绘制
					canvas.save();
					paint.setColor(color);
					canvas.drawColor(color);
					canvas.drawRect(0f, 0f,
							width,
							height,
							paint);
					paint.setColor(Color.WHITE);
					paint.setTextSize(200);
					canvas.drawText(new SimpleDateFormat ("HH : mm : ss").format(new Date()), width/2, height/2, paint);
					paint.setTextSize(80);
					canvas.drawText("#" + colorString, width/2, height/2 + 200, paint);
					canvas.restore();
				}
			}finally {
				if (canvas != null)
					holder.unlockCanvasAndPost(canvas);
			}
			handler.removeCallbacks(drawRunnable);
			if (isVisible) {
				handler.postDelayed(drawRunnable, 1 * 1000);
			}
		}
	}
}
