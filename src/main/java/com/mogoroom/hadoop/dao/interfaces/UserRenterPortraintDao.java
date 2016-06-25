package com.mogoroom.hadoop.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mogoroom.hadoop.dao.entity.UserRenterPortrait;


@Repository
public interface UserRenterPortraintDao extends JpaRepository<UserRenterPortrait, Serializable> {
	@Modifying 
	@Query(value="insert into user_renter_portrait(cellphone,userId,createTime) "
                +" select cellphone,userId,now() "
				+" from user_renter_portrait_day t "
				+" where not exists( "
				+"   select 1 from user_renter_portrait r "
				+"   where r.cellphone=t.cellphone "
				+" ) "
				+" group by cellphone,userId ",nativeQuery = true)
	void updateUserRenterPortrait();
	
	@Query(value=""
	  +"select rp.id,rp.createTime,rp.cellphone,rp.userId,ifnull(rp.active,0) active, "
	  +"( "
		  +" select district "
		  +" from user_renter_portrait_day t "
		  +" where 1=1 "
		  +"  and t.cellphone=rp.cellphone "
		  +" group by t.cellphone,t.userId,district   "
		  +" order by sum(districtCount) desc  "
		  +" limit 1 "
	  +" ) district, "
	  +" ( "
	  +"  select sum(districtCount) districtCount  "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,district   "
		+" 	order by sum(districtCount) desc  "
		+" 	limit 1 "
		+" ) districtCount, "
		+" ( "
		+"    select tradeArea "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,tradeArea   "
		+" 	order by sum(tradeAreaCount) desc  "
		+" 	limit 1 "
		+" ) tradeArea, "
		+" ( "
		+"    select sum(tradeAreaCount) tradeAreaCount "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,tradeArea   "
		+" 	order by sum(tradeAreaCount) desc  "
		+" 	limit 1 "
		+" ) tradeAreaCount, "
		+" ( "
		+"    select metroLine "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,metroLine   "
		+" 	order by sum(metroLineCount) desc  "
		+" 	limit 1 "
		+" ) metroLine, "
		+" ( "
		+"    select sum(metroLineCount) metroLineCount "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,metroLine   "
		+" 	order by sum(metroLineCount) desc  "
		+" 	limit 1 "
		+" ) metroLineCount, "
		+" ( "
		+"    select rentType "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,rentType   "
		+" 	order by sum(rentTypeCount) desc  "
		+" 	limit 1 "
		+" ) rentType, "
		+" ( "
		+"    select sum(rentTypeCount) rentTypeCount "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,rentType   "
		+" 	order by sum(rentTypeCount) desc  "
		+" 	limit 1 "
		+" ) rentTypeCount, "
		+" ( "
		+"    select roomType "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,roomType   "
		+" 	order by sum(roomTypeCount) desc  "
		+" 	limit 1 "
		+" ) roomType, "
		+" ( "
		+"    select sum(roomTypeCount) roomTypeCount "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,roomType   "
		+" 	order by sum(roomTypeCount) desc  "
		+" 	limit 1 "
		+" ) roomTypeCount, "
		+" ( "
		+"    select sum(startRoomPrice)/count(startRoomPrice) startRoomPrice "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId    "
		+" ) startRoomPrice, "
		+" ( "
		+"    select sum(endRoomPrice)/count(endRoomPrice) endRoomPrice "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId    "
		+" ) endRoomPrice, "
		+" ( "
		+"    select roomMate "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,roomMate   "
		+" 	order by sum(roomMateCount) desc  "
		+" 	limit 1 "
		+" ) roomMate, "
		+" ( "
		+"    select sum(roomMateCount) roomMateCount "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	 group by t.cellphone,t.userId,roomMate   "
		+" 	order by sum(roomMateCount) desc  "
		+" 	limit 1 "
		+" ) roomMateCount, "
		+" ( "
		+"    select hourseType "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,hourseType   "
		+" 	order by sum(hourseTypeCount) desc  "
		+" 	limit 1 "
		+" ) hourseType, "
		+" ( "
		+"    select sum(hourseTypeCount) hourseTypeCount "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,hourseType   "
		+" 	order by sum(hourseTypeCount) desc  "
		+" 	limit 1 "
		+" ) hourseTypeCount, "
		+" ( "
		+"    select roomSourceType "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,roomSourceType  " 
		+" 	order by sum(roomSourceTypeCount) desc  "
		+" 	limit 1 "
		+" ) roomSourceType, "
		+" ( "
		+"    select sum(roomSourceTypeCount) roomSourceTypeCount "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,roomSourceType   "
		+" 	order by sum(roomSourceTypeCount) desc  "
		+" 	limit 1 "
		+" ) roomSourceTypeCount, "
		+" ( "
		+"    select turnWay "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,turnWay   "
		+" 	order by sum(turnWayCount) desc  "
		+" 	limit 1 "
		+" ) turnWay, "
		+" ( "
		+"    select sum(turnWayCount) turnWayCount "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,turnWay   "
		+" 	order by sum(turnWayCount) desc  "
		+" 	limit 1 "
		+" ) turnWayCount, "
		+" ( "
		+"    select searchWord "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,searchWord   "
		+" 	order by sum(searchWordCount) desc  "
		+" 	limit 1 "
		+" ) searchWord, "
		+" ( "
		+"    select sum(searchWordCount) searchWordCount "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,searchWord   "
		+" 	order by sum(searchWordCount) desc  "
		+" 	limit 1 "
		+" ) searchWordCount, "
		+" ( "
		+"    select sortType "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,sortType   "
		+" 	order by sum(sortTypeCount) desc  "
		+" 	limit 1 "
		+" ) sortType, "
		+" ( "
		+"    select sum(sortTypeCount) sortTypeCount "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,sortType   "
		+" 	order by sum(sortTypeCount) desc  "
		+" 	limit 1 "
		+" ) sortTypeCount, "
		+" ( "
		+"    select isToilet "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,isToilet   "
		+" 	order by sum(isToiletCount) desc  "
		+" 	limit 1 "
		+" ) isToilet, "
		+" ( "
		+"    select sum(isToiletCount) isToiletCount "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,isToilet   "
		+" 	order by sum(isToiletCount) desc  "
		+" 	limit 1 "
		+" ) isToiletCount, "
		+" ( "
		+"    select isBalcony "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,isBalcony   "
		+" 	order by sum(isBalconyCount) desc  "
		+" 	limit 1 "
		+" ) isBalcony, "
		+" ( "
		+"    select sum(isBalconyCount) isBalconyCount "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,isBalcony   "
		+" 	order by sum(isBalconyCount) desc  "
		+" 	limit 1 "
		+" ) isBalconyCount, "
		+" ( "
		+"    select isAirConditioner "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,isAirConditioner   "
		+" 	order by sum(isAirConditionerCount) desc  "
		+" 	limit 1 "
		+" ) isAirConditioner, "
		+" ( "
		+"    select sum(isAirConditionerCount) isAirConditionerCount "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	group by t.cellphone,t.userId,isAirConditioner   "
		+" 	order by sum(isAirConditionerCount) desc  "
		+" 	limit 1 "
		+" ) isAirConditionerCount, "
		+" ( "
		+"    select lastLoginTime "
		+" 	from user_renter_portrait_day t "
		+" 	where 1=1 "
		+" 	  and t.cellphone=rp.cellphone "
		+" 	order by lastLoginTime desc  "
		+" 	limit 1  "
		+" )lastLoginTime "
     +" from user_renter_portrait rp "
	 +" where exists( "
	 +"  select 1 from user_renter_portrait_day rpd  "
	 +"  where rpd.cellphone=rp.cellphone "
	 +"    and rpd.userId=rp.userId "
	 +"    and rpd.createTime>=adddate(curdate(), '-2') "
	 +" )   "
	 +" order by rp.cellphone limit :start,:limit ",
     nativeQuery = true)
	public List<UserRenterPortrait> querySumUserRenterPortrait(@Param("start")long start,@Param("limit")long limit);
	
	@Query(value=""
			 +"select count(*) "
			 +" from user_renter_portrait rp "
			 +" where exists( "
			 +"  select 1 from user_renter_portrait_day rpd  "
			 +"  where rpd.cellphone=rp.cellphone "
			 +"    and rpd.userId=rp.userId "
			 +"    and rpd.createTime>=adddate(curdate(), '-2') "
			 +" )   ",
		     nativeQuery = true)
    public Long queryTotalSumUserRenterPortrait();
	
	
}
