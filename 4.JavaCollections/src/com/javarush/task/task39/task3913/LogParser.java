package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery{
    private Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    private Set<String[]> getLines(Date after, Date before) {
        Set<String[]> set = new HashSet<>();
        File folder = new File(logDir.toString());
        for (File f : folder.listFiles()) {
            if (Files.isRegularFile(f.toPath()) && f.getName().endsWith(".log")) {
                try {
                    BufferedReader reader = Files.newBufferedReader(f.toPath());
                    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    while (reader.ready()) {
                        String[] line = reader.readLine().split("\\t");
                        Date date = format.parse(line[2]);
                        if ((after == null || date.getTime() >= after.getTime())
                                && (before == null || date.getTime() <= before.getTime()))
                            set.add(line);
                    }
                    reader.close();
                } catch (Exception e) {
                }
            }
        }
        return set;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] l : lines)
            set.add(l[0]);
        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] l : lines)
            if (user.equals(l[1]))
                set.add(l[0]);
        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] l : lines)
            if (event.name().equals(l[3].split(" ")[0]))
                set.add(l[0]);
        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] l : lines)
            if (status.name().equals(l[4]))
                set.add(l[0]);
        return set;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(null, null);

        for (String[] line : lines)
            set.add(line[1]);
        return set;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            set.add(line[1]);
        return set.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[1].equals(user))
                set.add(line[3].split(" ")[0]);
        return set.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[0].equals(ip))
                set.add(line[1]);
        return set;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[3].equals(Event.LOGIN.name()))
                set.add(line[1]);
        return set;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[3].equals(Event.DOWNLOAD_PLUGIN.name()))
                set.add(line[1]);
        return set;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[3].equals(Event.WRITE_MESSAGE.name()))
                set.add(line[1]);
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[3].startsWith(Event.SOLVE_TASK.name()))
                set.add(line[1]);
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines) {
            String[] taskEvent = line[3].split(" ");
            if (taskEvent[0].equals(Event.SOLVE_TASK.name())) {
                int i = Integer.parseInt(taskEvent[1]);
                if (i == task)
                    set.add(line[1]);
            }
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[3].startsWith(Event.DONE_TASK.name()))
                set.add(line[1]);
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines) {
            String[] taskEvent = line[3].split(" ");
            if (taskEvent[0].equals(Event.DONE_TASK.name())) {
                int i = Integer.parseInt(taskEvent[1]);
                if (i == task)
                    set.add(line[1]);
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (String[] line : lines)
            try {
                if (line[3].startsWith(event.name()) && line[1].equals(user))
                    set.add(format.parse(line[2]));
            } catch (ParseException e) {
            }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (String[] line : lines)
            try {
                if (line[4].equals(Status.FAILED.name()))
                    set.add(format.parse(line[2]));
            } catch (ParseException e) {
            }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (String[] line : lines)
            try {
                if (line[4].equals(Status.ERROR.name()))
                    set.add(format.parse(line[2]));
            } catch (ParseException e) {
            }
        return set;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (String[] line : lines)
            try {
                if (line[3].equals(Event.LOGIN.name()) && line[1].equals(user))
                    set.add(format.parse(line[2]));
            } catch (ParseException e) {
            }
        Date firstDate = null;
        if (set.size() > 0) {
            firstDate = set.iterator().next();
            for (Date date : set)
                if (date.getTime() < firstDate.getTime())
                    firstDate = date;
        }
        return firstDate;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (String[] line : lines)
            try {
                if (line[3].startsWith(Event.SOLVE_TASK.name()) && line[1].equals(user) && Integer.parseInt(line[3].split(" ")[1]) == task)
                    set.add(format.parse(line[2]));
            } catch (ParseException e) {
            }
        Date firstDate = null;
        if (set.size() > 0) {
            firstDate = set.iterator().next();
            for (Date date : set)
                if (date.getTime() < firstDate.getTime())
                    firstDate = date;
        }
        return firstDate;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (String[] line : lines)
            try {
                if (line[3].startsWith(Event.DONE_TASK.name()) && line[1].equals(user) && Integer.parseInt(line[3].split(" ")[1]) == task)
                    set.add(format.parse(line[2]));
            } catch (ParseException e) {
            }
        Date firstDate = null;
        if (set.size() > 0) {
            firstDate = set.iterator().next();
            for (Date date : set)
                if (date.getTime() < firstDate.getTime())
                    firstDate = date;
        }
        return firstDate;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (String[] line : lines)
            try {
                if (line[3].equals(Event.WRITE_MESSAGE.name()) && line[1].equals(user))
                    set.add(format.parse(line[2]));
            } catch (ParseException e) {
            }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (String[] line : lines)
            try {
                if (line[3].equals(Event.DOWNLOAD_PLUGIN.name()) && line[1].equals(user))
                    set.add(format.parse(line[2]));
            } catch (ParseException e) {
            }
        return set;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            set.add(Event.valueOf(line[3].split(" ")[0]));
        return set;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[0].equals(ip))
            set.add(Event.valueOf(line[3].split(" ")[0]));
        return set;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[1].equals(user))
                set.add(Event.valueOf(line[3].split(" ")[0]));
        return set;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[4].equals(Status.FAILED.name()))
                set.add(Event.valueOf(line[3].split(" ")[0]));
        return set;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[4].equals(Status.ERROR.name()))
                set.add(Event.valueOf(line[3].split(" ")[0]));
        return set;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int i = 0;
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[3].startsWith(Event.SOLVE_TASK.name()) && Integer.parseInt(line[3].split(" ")[1]) == task)
                i++;
        return i;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int i = 0;
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[3].startsWith(Event.DONE_TASK.name()) && Integer.parseInt(line[3].split(" ")[1]) == task)
                i++;
        return i;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[3].startsWith(Event.SOLVE_TASK.name())) {
                Integer i = map.get(Integer.parseInt(line[3].split(" ")[1]));
                if (i != null) {
                    i++;
                    map.put(Integer.parseInt(line[3].split(" ")[1]), i);
                } else
                    map.put(Integer.parseInt(line[3].split(" ")[1]), 1);
            }
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<String[]> lines = getLines(after, before);

        for (String[] line : lines)
            if (line[3].startsWith(Event.DONE_TASK.name())) {
                Integer i = map.get(Integer.parseInt(line[3].split(" ")[1]));
                if (i != null) {
                    i++;
                    map.put(Integer.parseInt(line[3].split(" ")[1]), i);
                } else
                    map.put(Integer.parseInt(line[3].split(" ")[1]), 1);
            }
        return map;
    }

    @Override
    public Set<Object> execute(String query) {
        String[] queryParts = query.split(" ");
        Set<Object> result = new HashSet<>();

        if (queryParts[0].equals("get"))
            switch (queryParts[1]){
                case "ip": result.addAll(getUniqueIPs(null, null)); break;
                case "user": result.addAll(getAllUsers()); break;
                case "date": result.addAll(getAllDates()); break;
                case "event": result.addAll(getAllEvents(null, null)); break;
                case "status": result.addAll(getAllStatuses());
            }
        return result;
    }

    public Set<Date> getAllDates() {
        Set<Date> dates = new HashSet<>();
        Set<String[]> lines = getLines(null, null);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (String[] line : lines)
            try {
                dates.add(format.parse(line[2]));
            } catch (ParseException e) {
            }
        return dates;
    }

    public Set<Status> getAllStatuses(){
        Set<Status> set = new HashSet<>();
        Set<String[]> lines = getLines(null, null);
        for (String[] line : lines)
            set.add(Status.valueOf(line[4]));
        return set;
    }
}