package com.javayjx.service.base;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.javayjx.dao.base.ConfigDao;
import com.javayjx.entity.base.Config;


@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
	@Resource
	private ConfigDao configDao;

	
	/**
	 * @param curr      当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public  Config repalce( Config curr, Config origin){
		
		if(curr.getWebName() ==null){
			curr.setWebName(origin.getWebName());
		}
		if(curr.getStationName()==null){
			curr.setStationName(origin.getStationName());
		}
		if(curr.getCssVersion() ==null){
			curr.setCssVersion(origin.getCssVersion());
		}
		
		
		if(curr.getIndexTitle() ==null){
			curr.setIndexTitle(origin.getIndexTitle());
		}
		if(curr.getKeywords()==null){
			curr.setKeywords(origin.getKeywords());
		}
		if(curr.getDescription() ==null){
			curr.setDescription(origin.getDescription());
		}
		
		
		if(curr.getIndexPageSize() ==null){
			curr.setIndexPageSize(origin.getIndexPageSize());
		}
		if(curr.getAllpageSize()  ==null){
			curr.setAllpageSize(origin.getAllpageSize());
		}
		
		return curr;
	}
	
	@Override
	public Integer update(Config config) {
		Config origin = configDao.findId(config.getId());
		config = repalce(config, origin);
		configDao.save(config);
		return 1;
	}
	
	
	/**
	 * init 方法用这个
	 */
	@Override
	public Config findById(Integer id) {
		return configDao.findId(id);
	}

}
