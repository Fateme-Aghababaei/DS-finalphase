package pkg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class User {
    private String ID;
    private String name;
    private String password;
    private String dateOfBirth;
    private String uni;
    private String field;
    private String workPlace;
    private ArrayList<String> specialties;
    public ArrayList<String> connections;
    private HashMap<User, Integer> suggestionCandidates;

    private ArrayList<User> top20Suggestions;
    public int uniCoefficient;
    public int fieldCoefficient;
    public int workPlaceCoefficient;
    public int specialtiesCoefficient;
    public int levelCoefficient;

    public static ArrayList<User> AllUsers = new ArrayList<>();

    public User(String ID,
                String name,
                String password,
                String dateOfBirth,
                String uni,
                String field,
                String workPlace,
                ArrayList<String> specialties,
                ArrayList<String> connections) {
        this.ID = ID;
        this.name = name;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.uni = uni;
        this.field = field;
        this.workPlace = workPlace;
        this.specialties = specialties;
        this.connections = connections;
        this.uniCoefficient = 2;
        this.fieldCoefficient = 4;
        this.workPlaceCoefficient = 3;
        this.specialtiesCoefficient = 5;
        this.levelCoefficient = 5;
        suggestionCandidates = new HashMap<>();

    }

    public static User getUser(String ID) {
        for (User u : AllUsers) {
            if (u.getID().equals(ID))
                return u;
        }
        return null;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public ArrayList<String> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(ArrayList<String> specialties) {
        this.specialties = specialties;
    }

    public ArrayList<String> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<String> connections) {
        this.connections = connections;
    }

    public ArrayList<User> getTop20Suggestions() {
        return top20Suggestions;
    }

    public HashMap<User, Integer> GenerateSuggestionCandidates(int n) {
        if (n == 0) return new HashMap<>();
        for (String id : connections) {
            User u = getUser(id);
            suggestionCandidates.putIfAbsent(u, n);
            if (u != null) {
                HashMap<User, Integer> hm = u.GenerateSuggestionCandidates(n - 1);
                for (User u1 : hm.keySet()) {
                    suggestionCandidates.putIfAbsent(u1, hm.get(u1));
                }
            }
        }
        return suggestionCandidates;
    }

    public int getPriority(User user) {
        int priority = 0;
        if (this.uni.equals(user.uni))
            priority += uniCoefficient;
        if (this.workPlace.equals(user.workPlace))
            priority += workPlaceCoefficient;
        if (this.field.equals(user.field))
            priority += fieldCoefficient;
        int n = 0;
        for (String s : this.specialties) {
            if (user.specialties.contains(s))
                n++;
        }
        priority += specialtiesCoefficient * n;
        int level = suggestionCandidates.get(user) == null ? 0 : suggestionCandidates.get(user);
        level = level % 5 == 0 ? 5 : level % 5;
        priority += levelCoefficient * level;
        return priority;
    }

    public void GenerateTop20Suggestions() {
        suggestionCandidates.clear();
        PriorityQueue<User> pq = new PriorityQueue<>((u1, u2) -> getPriority(u2) - getPriority(u1));
        if(connections.size() == 0) {
            pq.addAll(User.AllUsers);
        }
        else {
            GenerateSuggestionCandidates(5);
            pq.addAll(suggestionCandidates.keySet());
        }
        top20Suggestions = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (pq.size() == 0) break;
            User u = pq.remove();
            if (u == this || connections.contains(u.getID())) continue;
            top20Suggestions.add(u);
        }
    }

    @Override
    public String toString() {
        return "[" + this.ID + "]: " + this.name;
    }
}
