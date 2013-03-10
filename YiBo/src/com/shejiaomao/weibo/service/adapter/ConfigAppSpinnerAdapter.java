package com.shejiaomao.weibo.service.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cattong.commons.ServiceProvider;
import com.cattong.entity.ConfigApp;
import net.dev123.yibo.R;
import com.shejiaomao.weibo.activity.AddAccountActivity;
import com.shejiaomao.weibo.db.ConfigAppDao;

public class ConfigAppSpinnerAdapter extends BaseAdapter {

	private AddAccountActivity context;
	
	private ConfigAppDao configAppDao;
	private LayoutInflater layoutInflater;
	private List<ConfigApp> apps;
	
	public ConfigAppSpinnerAdapter(AddAccountActivity context) {
		this.context = context;
		this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.configAppDao = new ConfigAppDao(context);
		setServiceProvider(context.getSp());
	}
	
	public void setServiceProvider(ServiceProvider serviceProvider) {
		if (serviceProvider == null) {
			return;
		}
		
		apps = configAppDao.findApps(serviceProvider);
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return apps == null ? 0 : apps.size();
	}

	@Override
	public ConfigApp getItem(int position) {
		return apps == null ? null : apps.get(position);
	}

	@Override
	public long getItemId(int position) {
		return apps == null ? 0L : apps.get(position).getAppId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.list_item_app_key, null);
		}
		
		TextView tvAppKeyName = (TextView) convertView.findViewById(R.id.tvAppKeyName);
		tvAppKeyName.setText(getItem(position).getAppName());
		
		return convertView;
	}

	@Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

}
