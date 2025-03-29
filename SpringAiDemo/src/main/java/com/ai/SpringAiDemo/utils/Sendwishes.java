package com.ai.SpringAiDemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Sendwishes {

    // Method 1: Group → (Date → Birthday Message)
    public Map<String, Map<String, String>> getBirthdayMessagesByGroup() {
        Map<String, Map<String, String>> groupBirthdayMap = new HashMap<>();

        // 🎉 Kadapa Buddies
//        Map<String, String> kadapaMap = new HashMap<>();
//        kadapaMap.put("01-10", "Happy birthday to you Mr. Nagaraju Valasa");
//        kadapaMap.put("02-04", "Happy birthday to you Mr. Srinivasulu Penumadu");
//        kadapaMap.put("26-04", "Happy birthday to you Mr. Nagendra Kumar Adapala");
//        kadapaMap.put("30-04", "Happy birthday to you Mr. Vidya Sagar Pasupuleti");
//        kadapaMap.put("24-06", "Happy birthday to you Mr. Harish Kumar Kalisetty");
//        kadapaMap.put("09-09", "Happy birthday to you Mr. Sreenivasulu Bandi");
//        kadapaMap.put("13-09", "Happy birthday to you Mr. Sreekanth Chennakesavula");
//        groupBirthdayMap.put("Kadapa Buddies", kadapaMap);
//
//        // 🎉 bloreMap Team
//        Map<String, String> bloreMap = new HashMap<>();
//        bloreMap.put("26-04", "Happy birthday to you Mr. Nagendra Kumar Adapala");
//        bloreMap.put("24-06", "Happy birthday to you Mr. Harish Kumar Kalisetty");
//        bloreMap.put("13-09", "Happy birthday to you Mr. Sreekanth Chennakesavula");
//        groupBirthdayMap.put("Youth 90's Batch", bloreMap);
        
        
        //Home Map
        Map<String, String> homeMap = new HashMap<>();
        homeMap.put("26-04", "Happy birthday to you Mr. Nagendra Kumar Adapala");
        homeMap.put("24-08", "Happy birthday to you Mrs. Anjani Surekha Pothula");
        homeMap.put("12-08", "Happy birthday to you Mr. Venkata Maruthi Sowmyanath Adapala");
        homeMap.put("07-05", "Happy birthday to you Miss. Kiranmayi Rajeshwari Adapala");
        groupBirthdayMap.put("RASNA", homeMap);

        return groupBirthdayMap;
    }

    // Method 2: Group name → Country
    public Map<String, String> getGroupList() {
        Map<String, String> groupMap = new LinkedHashMap<>();
        //groupMap.put("Kadapa Buddies", "CAN");
		//groupMap.put("Gundachari school SSC92", "IND");
		groupMap.put("RASNA", "CAN");
		//groupMap.put("Youth 90's Batch", "IND");
        return groupMap;
    }

    // Method 3: Decision logic
    public String getMessageForGroup(String groupName, String country) {
        String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        Map<String, Map<String, String>> birthdayMessagesByGroup = getBirthdayMessagesByGroup();

        // Check if this group has birthday messages
        if (birthdayMessagesByGroup.containsKey(groupName)) {
            Map<String, String> groupBirthdays = birthdayMessagesByGroup.get(groupName);
            if (groupBirthdays.containsKey(today)) {
                return groupBirthdays.get(today); // 🎂 Send birthday message for today
            }
        }

        // 🎯 No birthday today for this group → return default by country
        if ("IND".equalsIgnoreCase(country)) {
            return "Good Evening All Friends";
        } else {
            return "Hi, All Good morning friends";
        }
    }
}
