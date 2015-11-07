package com.cyq7on.mushrommstreet.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cyq7on.mushrommstreet.bean.DynamicVo;
import com.cyq7on.mushrommstreet.utils.DeviceInfo;
import com.cyq7on.mushroomstreet.AppConfig;
import com.example.mushroomstreet.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DynamicAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<DynamicVo> list;
	private Context mContext;

	public DynamicAdapter(Context context, List<DynamicVo> list) {
		this.list = list;
		this.mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public DynamicVo getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();

			convertView = mInflater.inflate(R.layout.mime_share_item, parent,
					false);
			holder.ivUser = (ImageView) convertView.findViewById(R.id.iv_user);
			holder.tvUserName = (TextView) convertView
					.findViewById(R.id.username);
			holder.tvDate = (TextView) convertView
					.findViewById(R.id.contenttime);
			holder.btnAttention = (Button) convertView
					.findViewById(R.id.btn_attention);
			holder.llContentImage = (LinearLayout) convertView
					.findViewById(R.id.image_container);
			holder.btnGoods = (Button) convertView.findViewById(R.id.btn_goods);
			holder.ivDianzan = (ImageView) convertView
					.findViewById(R.id.iv_dianzan);
			holder.tvDianzanNum = (TextView) convertView
					.findViewById(R.id.tv_dianzan);
			holder.btnComment = (Button) convertView
					.findViewById(R.id.btn_comment);
			holder.tvLookAll = (TextView) convertView
					.findViewById(R.id.tv_comment_num);
			holder.tvCommentPeople = (TextView) convertView
					.findViewById(R.id.tv_comment_people);
			holder.tvContent = (TextView) convertView
					.findViewById(R.id.tv_comment);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		int width = (int) DeviceInfo.getScreenWidth(mContext);// 屏幕宽度
		ImageView image;
		if (list.get(position).getContentImageurl().size() == 1) {
			holder.llContentImage.removeAllViews();
			image = new ImageView(mContext);
			image.setOnClickListener(new ImageOnClickListener(0));
			image.setScaleType(ScaleType.FIT_XY);
			int hight = (int) DeviceInfo.getScreenHeight(mContext);// 屏幕高度
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT, hight / 4);
			holder.llContentImage.addView(image, params);
			ImageLoader.getInstance().displayImage(
					list.get(position).getContentImageurl().get(0), image,
					AppConfig.options);
		} else if (list.get(position).getContentImageurl().size() == 2) {
			holder.llContentImage.removeAllViews();

			LinearLayout horizonLayout = new LinearLayout(mContext);
			LinearLayout.LayoutParams params;
			float density = DeviceInfo.getDensity(mContext);
			float imageLayWidth = width - (10 + 10) * density;
			for (int i = 0; i < list.get(position).getContentImageurl().size(); i++) {
				params = new LinearLayout.LayoutParams(
						(int) (imageLayWidth / 2), (int) (imageLayWidth / 2));
				image = new ImageView(mContext);
				image.setOnClickListener(new ImageOnClickListener(i));
				image.setScaleType(ScaleType.FIT_XY);
				image.setPadding(0, (int) (4 * density), (int) (4 * density),
						(int) (4 * density));
				horizonLayout.addView(image, params);
				ImageLoader.getInstance().displayImage(
						list.get(position).getContentImageurl().get(i), image,
						AppConfig.options);
			}
			holder.llContentImage.addView(horizonLayout);
		} else if (list.get(position).getContentImageurl().size() > 2
				&& list.get(position).getContentImageurl().size() <= 9) {
			holder.llContentImage.removeAllViews();

			LinearLayout.LayoutParams params;
			float density = DeviceInfo.getDensity(mContext);
			float imageLayWidth = width - (10 + 10) * density;

			int size = list.get(position).getContentImageurl().size();
			int yuShu = size % 3;
			if (yuShu == 0) {
				int hangNum = (int) (size / 3);
				for (int i = 0; i < hangNum; i++) {
					LinearLayout horizonLayout = new LinearLayout(mContext);
					for (int j = 0; j < 3; j++) {
						params = new LinearLayout.LayoutParams(
								(int) (imageLayWidth / 3),
								(int) (imageLayWidth / 3));
						image = new ImageView(mContext);
						image.setOnClickListener(new ImageOnClickListener(i * 3
								+ j));
						image.setScaleType(ScaleType.FIT_XY);
						image.setPadding(0, (int) (2 * density),
								(int) (2 * density), (int) (2 * density));
						horizonLayout.addView(image, params);
						ImageLoader.getInstance().displayImage(
								list.get(position).getContentImageurl()
										.get(i * 3 + j), image,
								AppConfig.options);
					}
					holder.llContentImage.addView(horizonLayout);
				}
			} else {
				int hangNum = (int) (size / 3) + 1;
				for (int i = 0; i <= hangNum - 1; i++) {
					LinearLayout horizonLayout = new LinearLayout(mContext);
					if (i < hangNum - 1) {
						for (int j = 0; j < 3; j++) {
							params = new LinearLayout.LayoutParams(
									(int) (imageLayWidth / 3),
									(int) (imageLayWidth / 3));
							image = new ImageView(mContext);
							image.setOnClickListener(new ImageOnClickListener(i * 3
									+ j));
							image.setPadding(0, (int) (2 * density),
									(int) (2 * density), (int) (2 * density));
							image.setScaleType(ScaleType.FIT_XY);
							horizonLayout.addView(image, params);
							ImageLoader.getInstance().displayImage(
									list.get(position).getContentImageurl()
											.get(i * 3 + j), image,
									AppConfig.options);
						}
						holder.llContentImage.addView(horizonLayout);
					} else if (i == hangNum - 1) {
						for (int j = 0; j < yuShu; j++) {
							params = new LinearLayout.LayoutParams(
									(int) (imageLayWidth / 3),
									(int) (imageLayWidth / 3));
							image = new ImageView(mContext);
							image.setOnClickListener(new ImageOnClickListener(i * 3
									+ j));
							image.setScaleType(ScaleType.FIT_XY);
							horizonLayout.addView(image, params);
							ImageLoader.getInstance().displayImage(
									list.get(position).getContentImageurl()
											.get(i * 3 + j), image,
									AppConfig.options);
						}
						holder.llContentImage.addView(horizonLayout);
					}
				}
			}
		}

		try {
			ImageLoader.getInstance().displayImage(list.get(position).getUrl(),
					holder.ivUser, AppConfig.options);

			holder.tvUserName.setText(list.get(position).getUserName());
			holder.tvDate.setText(list.get(position).getDate());
			holder.tvPlace.setText(list.get(position).getTvPlace());
			holder.tvContent.setText(list.get(position).getTvContent());
			holder.btnGoods.setText(list.get(position).getGoods() + "件相关商品");
			holder.tvDianzanNum.setText(list.get(position).getDianzanNum());
			holder.tvLookAll.setText("查看所有" + list.get(position).getComment()
					+ "条评论");
			holder.tvCommentPeople.setText(list.get(position)
					.getCommentPeople());
			holder.tvComment.setText(list.get(position).getContent());
		} catch (Exception e) {
			// TODO: handle exception
		}

		holder.tvUserName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, list.get(position).getUserName(),
						Toast.LENGTH_LONG).show();
			}
		});

		holder.ivUser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, list.get(position).getUserName(),
						Toast.LENGTH_LONG).show();
			}
		});

		holder.btnAttention.setOnClickListener(new OnClickListener() {
			boolean isAttention = false;

			@Override
			public void onClick(View v) {
				if (!isAttention) {
					holder.btnAttention.setText("已关注");
					holder.btnAttention
							.setTextColor(android.graphics.Color.DKGRAY);
					isAttention = true;
				} else {
					holder.btnAttention.setText("+ 关注");
					holder.btnAttention.setTextColor(Color
							.parseColor("#000000"));
					isAttention = false;
				}
			}
		});

		holder.ivDianzan.setBackgroundResource(R.drawable.like_icon_grey);
		holder.ivDianzan.setOnClickListener(new OnClickListener() {
			boolean b = false;

			@Override
			public void onClick(View v) {
				if (!b) {
					holder.ivDianzan
							.setBackgroundResource(R.drawable.like_icon_red);
					b = true;
				} else {
					holder.ivDianzan
							.setBackgroundResource(R.drawable.like_icon_grey);
					b = false;
				}
			}
		});

		holder.btnGoods.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "评论" + list.get(position).getGoods(),
						Toast.LENGTH_LONG).show();
			}
		});

		holder.btnComment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mContext,
						"评论" + list.get(position).getUserName(),
						Toast.LENGTH_LONG).show();
			}
		});

		holder.tvLookAll.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "查看所有评论", Toast.LENGTH_LONG).show();
			}
		});

		holder.tvCommentPeople.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mContext,
						"我是:" + list.get(position).getCommentPeople(),
						Toast.LENGTH_LONG).show();
			}
		});

		return convertView;
	}

	private class ImageOnClickListener implements OnClickListener {

		private int index;

		public ImageOnClickListener(int index) {
			this.index = index;
		}

		@Override
		public void onClick(View v) {
			Toast.makeText(mContext, "第" + index + "张", Toast.LENGTH_LONG)
					.show();
		}

	}

	private class ViewHolder {
		public ImageView ivUser;// 用戶头像
		public TextView tvUserName;// 用户名
		public TextView tvDate;// 动态时间
		public TextView tvPlace;// 动态地址
		public Button btnAttention;// 关注按钮
		public TextView tvContent;// 动态内容
		public LinearLayout llContentImage;// 承载动态里图片的线性布局
		public Button btnGoods;// 相关商品
		public ImageView ivDianzan;// 点赞图标
		public TextView tvDianzanNum;// 点赞数量
		public Button btnComment;// 评论按钮
		public TextView tvLookAll;// 查看所有评论
		public TextView tvCommentPeople;// 评论人
		public TextView tvComment;// 评论内容
	}
}
