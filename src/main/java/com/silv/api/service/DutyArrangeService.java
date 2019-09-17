package com.silv.api.service;

import com.silv.api.dto.Arrange3To2Dto;
import com.silv.api.model.DutyArrangeDetails;
import com.silv.api.model.Result;
import com.silv.api.util.DateUtils;
import com.silv.api.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DutyArrangeService {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    Random random = new Random();

    public Result get3to2Arrange(Arrange3To2Dto arrange) {
        // 三班两倒 3个领导，3个班长，6个员工，共12个人
        List<DutyArrangeDetails> arrangeDetailsList = sortPerson(arrange.getType(), arrange.getLeader(), arrange.getMonitor(), arrange.getStaff());

        // 获取当月天数
        int currentDays = 0;
        try {
            currentDays = DateUtils.getDaysOfMonth(sdf.parse("2020-02"));
        } catch (ParseException e) {
            return ResultUtil.error("日期解析异常");
        }
        // 获取当月排班多余的天数
        int redundantDays = currentDays % 3;
        // 获取当月需要排班的天数
        int arrangeDays = currentDays - redundantDays;
        // 早班、晚班、休息相当于是3倍的天数，先轮排早班和晚班
        int days = arrangeDays * 2;
        int i = 0;
        List<DutyArrangeDetails> list = new ArrayList<>();
        for (int j = 0; j <= days; j++) {
            for (int k = 0; k <= arrangeDetailsList.size() - 1; k++) {
                DutyArrangeDetails arrangeDetails = arrangeDetailsList.get(j);
                DutyArrangeDetails details = new DutyArrangeDetails();
                BeanUtils.copyProperties(arrangeDetails, details);
                details.setBatch(i + 1);
                list.add(details);
                i++;
                break;
            }
            if (j == arrangeDetailsList.size() - 1) {
                //判断j的值，重复循环
                j = -1;//因为j++,数组从0开始取值，所以j=-1,
            }
            if (i == days) {
                break;
            }
            continue;
        }
        // 将数据分批次（对应某一天）、分类型
        for (DutyArrangeDetails arrangeDetails : list) {
            if (arrangeDetails.getBatch() % 2 == 0) {
                arrangeDetails.setType(2);
                arrangeDetails.setBatch(arrangeDetails.getBatch() / 2);
            } else {
                arrangeDetails.setType(1);
                arrangeDetails.setBatch((arrangeDetails.getBatch() + 1) / 2);
            }
        }

        // 将排班按照批次分成不同的map
        Map<Integer, List<DutyArrangeDetails>> map = new HashMap();
        for (int m = 1; m <= arrangeDays; m++) {
            List<DutyArrangeDetails> list1 = new ArrayList<>();
            for (DutyArrangeDetails arrangeDetails : list) {
                if (m == arrangeDetails.getBatch()) {
                    list1.add(arrangeDetails);
                    if (list1.size() == 2) {
                        break;
                    }
                }
            }
            map.put(m, list1);
        }

        // 三班两倒之前是只分早班晚班，现在把多余的一组转成休息班放进对应的map
        for (int n = 1; n <= arrangeDays; n++) {
            List<DutyArrangeDetails> arrangeDetails = map.get(n);
            List<Integer> stringList = new ArrayList<>();
            for (DutyArrangeDetails dutyArrangeDetails : arrangeDetails) {
                stringList.add(dutyArrangeDetails.getLeaderId());
            }
            for (DutyArrangeDetails dutyArrangeDetails : arrangeDetailsList) {
                if (!stringList.contains(dutyArrangeDetails.getLeaderId())) {
                    DutyArrangeDetails detail = new DutyArrangeDetails();
                    BeanUtils.copyProperties(dutyArrangeDetails, detail);
                    detail.setBatch(n);
                    detail.setType(3);
                    arrangeDetails.add(detail);
                }
            }
        }

        // 将map转为list准备入库
        List<DutyArrangeDetails> resultList = new ArrayList<>();
        for (Integer key : map.keySet()) {
            resultList.addAll(map.get(key));
        }
        for (DutyArrangeDetails dutyArrangeDetails : resultList) {
            System.out.println(dutyArrangeDetails.toString());
        }
        return ResultUtil.success(resultList);
    }

    public Result get4to3Arrange() {
        // 四班三倒 4个领导，4个班长，8个员工，共16个人
        List<String> leader = new ArrayList<>();
        List<String> monitor = new ArrayList<>();
        List<String> staff = new ArrayList<>();
        leader.add("领导1");
        leader.add("领导2");
        leader.add("领导3");
        leader.add("领导4");
        monitor.add("班长1");
        monitor.add("班长2");
        monitor.add("班长3");
        monitor.add("班长4");
        staff.add("员工1");
        staff.add("员工2");
        staff.add("员工3");
        staff.add("员工4");
        staff.add("员工5");
        staff.add("员工6");
        staff.add("员工7");
        staff.add("员工8");

//        List<DutyArrangeDetails> arrangeDetailsList = sortPerson(2, leader, monitor, staff);
        List<DutyArrangeDetails> arrangeDetailsList = new ArrayList<>();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        // 获取当月天数
        int currentDays = 0;
        try {
            currentDays = DateUtils.getDaysOfMonth(sdf.parse("2020-02"));
        } catch (ParseException e) {
//            return ResultUtil.error("日期解析异常");
        }
        // 获取当月排班多余的天数
        int redundantDays = currentDays % 4;
        System.out.println(redundantDays);
        // 获取当月需要排班的天数
        int arrangeDays = currentDays - redundantDays;
        // 早班、晚班、休息相当于是3倍的天数，先轮排早班和晚班
        int days = arrangeDays * 3;
        int i = 0;
        List<DutyArrangeDetails> list = new ArrayList<>();
        for (int j = 0; j <= days; j++) {
            for (int k = 0; k <= arrangeDetailsList.size() - 1; k++) {
                DutyArrangeDetails arrangeDetails = arrangeDetailsList.get(j);
                DutyArrangeDetails details = new DutyArrangeDetails();
                BeanUtils.copyProperties(arrangeDetails, details);
                details.setBatch(i + 1);
                list.add(details);
                i++;
                break;
            }
            if (j == arrangeDetailsList.size() - 1) {
                //判断j的值，重复循环
                j = -1;//因为j++,数组从0开始取值，所以j=-1,
            }
            if (i == days) {
                break;
            }
            continue;
        }
        // 将数据分批次（对应某一天）、分类型
        for (DutyArrangeDetails arrangeDetails : list) {
            if (arrangeDetails.getBatch() % 3 == 0) {
                arrangeDetails.setType(3);
                arrangeDetails.setBatch(arrangeDetails.getBatch() / 3);
            } else if ((arrangeDetails.getBatch() + 1) % 3 == 0) {
                arrangeDetails.setType(2);
                arrangeDetails.setBatch((arrangeDetails.getBatch() + 1) / 3);
            } else {
                arrangeDetails.setType(1);
                arrangeDetails.setBatch((arrangeDetails.getBatch() + 2) / 3);
            }
        }

        // 将排班按照批次分成不同的map
        Map<Integer, List<DutyArrangeDetails>> map = new HashMap();
        for (int m = 1; m <= arrangeDays; m++) {
            List<DutyArrangeDetails> list1 = new ArrayList<>();
            for (DutyArrangeDetails arrangeDetails : list) {
                if (m == arrangeDetails.getBatch()) {
                    list1.add(arrangeDetails);
                    if (list1.size() == 3) {
                        break;
                    }
                }
            }
            map.put(m, list1);
        }

        // 四班三倒之前是只分早班晚班，现在把多余的一组转成休息班放进对应的map
        for (int n = 1; n <= arrangeDays; n++) {
            List<DutyArrangeDetails> arrangeDetails = map.get(n);
            List<Integer> stringList = new ArrayList<>();
            for (DutyArrangeDetails dutyArrangeDetails : arrangeDetails) {
                stringList.add(dutyArrangeDetails.getLeaderId());
            }
            for (DutyArrangeDetails dutyArrangeDetails : arrangeDetailsList) {
                if (!stringList.contains(dutyArrangeDetails.getLeaderId())) {
                    DutyArrangeDetails detail = new DutyArrangeDetails();
                    BeanUtils.copyProperties(dutyArrangeDetails, detail);
                    detail.setBatch(n);
                    detail.setType(4);
                    arrangeDetails.add(detail);
                }
            }
        }

        // 将map转为list准备入库
        List<DutyArrangeDetails> resultList = new ArrayList<>();
        for (Integer key : map.keySet()) {
            resultList.addAll(map.get(key));
        }
        for (DutyArrangeDetails dutyArrangeDetails : resultList) {
            System.out.println(dutyArrangeDetails.toString());
        }
        return ResultUtil.success();
    }

    public Result get5to2Arrange() {

        return ResultUtil.success();
    }

    public List<DutyArrangeDetails> sortPerson(int type, Integer[] leader1, Integer[] monitor1, Integer[] staff1) {
        List<Integer> leader = new ArrayList<>(Arrays.asList(leader1));
        List<Integer> monitor = new ArrayList<>(Arrays.asList(monitor1));
        List<Integer> staff = new ArrayList<>(Arrays.asList(staff1));
        int j = 0;
        if (type == 1) {
            j = 3;
        } else if (type == 2) {
            j = 4;
        }
        // 将人员随机分成四组
        List<DutyArrangeDetails> arrangeDetailsList = new ArrayList<>();
        for (int i = 0; i < j; i++) {
            DutyArrangeDetails dutyArrangeDetails = new DutyArrangeDetails();
            int leader_size = random.nextInt(leader.size());
            dutyArrangeDetails.setLeaderId(leader.get(leader_size));
            leader.remove(leader_size);
            int monitor_size = random.nextInt(monitor.size());
            dutyArrangeDetails.setMonitorId(monitor.get(monitor_size));
            monitor.remove(monitor_size);
            int size1 = random.nextInt(staff.size());
            dutyArrangeDetails.setStaffId(staff.get(size1) + ",");
            staff.remove(size1);
            int size2 = random.nextInt(staff.size());
            dutyArrangeDetails.setStaffId(dutyArrangeDetails.getStaffId() + staff.get(size2));
            staff.remove(size2);
            arrangeDetailsList.add(dutyArrangeDetails);
        }
        return arrangeDetailsList;
    }


}
