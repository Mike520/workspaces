package com.itheima.redbaby;

import java.util.ArrayList;

import com.itheima.redbaby.ui.MainActivity;
import com.itheima.redbaby.utils.AnimationController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

/**
 * 如果是第一次进入app，则会出现此页面
 * 
 * @author my
 * 
 */
public class Splashctivity extends Activity {

	/**
	 * viewPager
	 */
	private ViewPager vp_splash;
	/**
	 * 存放ImageView
	 */
	private ArrayList<ImageView> imageViewList;
	/**
	 * 存放Image的int
	 */
	private int[] imageResourseIDs;
	/**
	 * adapter
	 */
	private ViewPagerAdapter adapter;
	private Button btn_enter;
	private ImageView iv_s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		init();
	}

	private void init() {
		vp_splash = (ViewPager) findViewById(R.id.dl_vp_splash);
		btn_enter = (Button) findViewById(R.id.dd_splash_enter_homePage);
		iv_s = (ImageView) findViewById(R.id.dd_iv_s);
		prepareData();
//		ViewPager
		btn_enter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Splashctivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		btn_enter.setVisibility(View.GONE);
		iv_s.setVisibility(View.VISIBLE);
		iv_s.setImageResource(R.drawable.dd_guide_1_l);
		AnimationController.scaleIn(iv_s, 200, 0);
	}

	/**
	 * 准备数据
	 */
	private void prepareData() {
		imageViewList = new ArrayList<ImageView>();
		imageResourseIDs = getImageResourseIDs();

		ImageView iv;
		// 点view
		View view;
		for (int i = 0; i < imageResourseIDs.length; i++) {
			iv = new ImageView(this);
			iv.setBackgroundResource(imageResourseIDs[i]);
			imageViewList.add(iv);
			// 添加点view对象
			view = new View(this);
			view.setBackgroundDrawable(getResources().getDrawable(R.drawable.point_background));
			LayoutParams lp = new LayoutParams(5, 5);
			lp.leftMargin = 5;
			view.setLayoutParams(lp);
			view.setEnabled(false);
		}

		adapter = new ViewPagerAdapter();
		vp_splash.setAdapter(adapter);
		vp_splash.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					btn_enter.setVisibility(View.GONE);
					iv_s.setVisibility(View.VISIBLE);
					iv_s.setImageResource(R.drawable.dd_guide_1_l);
					AnimationController.scaleIn(iv_s, 200, 0);
					break;
				case 1:
					btn_enter.setVisibility(View.GONE);
					iv_s.setVisibility(View.VISIBLE);
					iv_s.setImageResource(R.drawable.dd_guide_2_l);
					AnimationController.scaleIn(iv_s, 200, 0);

					break;
				case 2:
					iv_s.setVisibility(View.VISIBLE);
					iv_s.setImageResource(R.drawable.dd_guide_3_l);
					AnimationController.scaleIn(iv_s, 200, 0);
					btn_enter.setVisibility(View.VISIBLE);

					break;

				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	/**
	 * 从服务器中拿viewPager的图片
	 * 
	 * @return
	 */
	private int[] getImageResourseIDs() {
		// TODO
		return new int[] { R.drawable.dd_guide_1, R.drawable.dd_guide_2, R.drawable.dd_guide_3 };
	}

	private class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageResourseIDs.length;
		}

		/**
		 * 判断出去的view是否等于是否等于进来的view，如果为true，则复用
		 */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		/**
		 * 销毁预加载以外的view对象，会把需要销毁的对象的索引位对象销毁
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(imageViewList.get(position % imageViewList.size()));
		}

		/**
		 * 创建一个view
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(imageViewList.get(position % imageViewList.size()));
			return imageViewList.get(position % imageViewList.size());
		}

	}

}
