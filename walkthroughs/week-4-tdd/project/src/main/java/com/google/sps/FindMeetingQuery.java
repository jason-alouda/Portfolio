// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.Comparator;
import java.util.*;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    
    // Arraylist of all available times for meeting request
    ArrayList<TimeRange> available_times = new ArrayList<TimeRange>();

    // Do a set intersection to extract relevant events
    ArrayList<Event> relevant_events = new ArrayList<Event>();
    for (Event ev : events){
        Set<String> intersection = new HashSet<String>(ev.getAttendees());
        intersection.retainAll(request.getAttendees());
        if (intersection.size() > 0){
            relevant_events.add(ev);
        }
    }

    // Sort events by order of their starting time, then by ending time. 
    Comparator<Event> cmp = Comparator.comparing(Event::getWhen, TimeRange.ORDER_BY_START);
    Collections.sort(relevant_events, cmp);

    int curr_time = TimeRange.START_OF_DAY;
    for (Event ev : relevant_events){
        //System.out.println("relevant event:");
        //System.out.println("Start: " + ev.getWhen().start());
        //System.out.println("End: " + ev.getWhen().end());
        //System.out.println("Curr time " + curr_time);
        if (ev.getWhen().start() > curr_time){
            TimeRange slot = TimeRange.fromStartEnd(curr_time, ev.getWhen().start(), false);
            if (slot.duration() >= request.getDuration()){
                available_times.add(slot);
            }
        }
        if (ev.getWhen().end() > curr_time){
            curr_time = ev.getWhen().end();
        }
    }

    if (curr_time < TimeRange.END_OF_DAY){
        TimeRange slot = TimeRange.fromStartEnd(curr_time, TimeRange.END_OF_DAY, true);
        if (slot.duration() >= request.getDuration()){
            available_times.add(slot);
            //System.out.println("Slot added");
        }
    }
    //System.out.println("--------NEXT---------");
    return available_times;
  }
}
  
