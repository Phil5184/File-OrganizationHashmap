import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSystem {

    MyHashMap<String, ArrayList<FileData>> nameMap;
    MyHashMap<String, ArrayList<FileData>> dateMap;

    // TODO
    public FileSystem() {
    	this.nameMap = new MyHashMap<>();
    	this.dateMap = new MyHashMap<>();
    }

    // TODO
    public FileSystem(String inputFile) {
        // Add your code here
    	this.nameMap = new MyHashMap<>();
    	this.dateMap = new MyHashMap<>();
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                // Add your code here
                System.out.println(data[0]);
                FileData f1 = new FileData(data[0], data[1], data[2]);
                this.add(f1.name, f1.dir, f1.lastModifiedDate);
                // for nameMao
                //if name not already in nameMao, then add it in with put, if it is then add the fileData to the arrayList in value using replace
                /*if (this.nameMap.containsKey(f1.name)) {
                	ArrayList<FileData> nameList = this.nameMap.get(f1.name);
                	nameList.add(f1);
                	this.nameMap.replace(f1.name, nameList);
                }
                else {
                	ArrayList<FileData> nameValue = new ArrayList<>();
                	nameValue.add(f1);
                	this.nameMap.put(f1.name, nameValue);
                }
                //for dateMap
                // if date not already in dateMap, add it in with put. If it is then add it to the arrayList in value using replace
                if (this.dateMap.containsKey(f1.lastModifiedDate)) {
                	ArrayList<FileData> dateList = this.dateMap.get(f1.lastModifiedDate);
                	dateList.add(f1);
                	this.dateMap.replace(f1.lastModifiedDate, dateList);
                }
                else {
                	ArrayList<FileData> dateValue = new ArrayList<>();
                	dateValue.add(f1);
                	this.dateMap.put(f1.lastModifiedDate, dateValue);
                }*/
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    // TODO
    public boolean add(String fileName, String directory, String modifiedDate) {
    	if (fileName == null) {
    		fileName = "";
    	}
    	if (directory == null) {
    		directory = "/";
    	}
    	if (modifiedDate == null) {
    		modifiedDate = "01/01/2021";
    	}
    	boolean willReturn = false;
    	boolean willReturn1 = false;
    	FileData toAdd = new FileData(fileName, directory, modifiedDate);
    	if (nameMap.containsKey(toAdd.name)) {
    		ArrayList<FileData> toCheck = this.nameMap.get(toAdd.name);
    		for (int n = 0; n < toCheck.size(); n++) {
    			if (toCheck.get(n).dir.equals(directory)) {
    				return false;
    			}
    		}
        	toCheck.add(toAdd);
        	this.nameMap.replace(toAdd.name, toCheck);
    		willReturn =  true;
    	}
    	else {
    		ArrayList<FileData> listAdd = new ArrayList<>();
        	listAdd.add(toAdd);
    		this.nameMap.put(toAdd.name, listAdd);
    		willReturn = true;
    	}
    	//do for dateMap
    	if (dateMap.containsKey(toAdd.lastModifiedDate)) {
    		ArrayList<FileData> toCheckdate = this.dateMap.get(toAdd.lastModifiedDate);
    		toCheckdate.add(toAdd);
    		this.dateMap.replace(toAdd.lastModifiedDate, toCheckdate);
    		willReturn1 = true;
    	}
    	else {
    		ArrayList<FileData> listAddDate = new ArrayList<>();
        	listAddDate.add(toAdd);
    		this.dateMap.put(toAdd.lastModifiedDate, listAddDate);
    		willReturn1 = true;
    	
    	}
    	return willReturn && willReturn1;
    }

    // TODO
    public FileData findFile(String name, String directory) {
    	if (nameMap.containsKey(name)) {
    		ArrayList<FileData> toCheck = nameMap.get(name);
    		for (int k = 0; k < toCheck.size(); k +=1) {
    			if(toCheck.get(k).dir.equals(directory)) {
    				return toCheck.get(k);
    			}
    		}
    	}
    	return null;
    }

    // TODO
    public ArrayList<String> findAllFilesName() {
    	if (this.nameMap.size() == 0) {
    		ArrayList<String> returned = new ArrayList<>();
    		return returned;
    	}
    	else {
    		ArrayList<String> returned1 = (ArrayList<String>) this.nameMap.keys();
    		return returned1;
    	}
    }

    // TODO
    public ArrayList<FileData> findFilesByName(String name) {
    	if (nameMap.containsKey(name)) {
    		return nameMap.get(name);
    	}
    	else {
    		ArrayList<FileData> empty = new ArrayList<>();
    		return empty;
    	}
    	
    }

    // TODO
    public ArrayList<FileData> findFilesByDate(String modifiedDate) {
    	if (dateMap.containsKey(modifiedDate)) {
    		return dateMap.get(modifiedDate);
    	}
    	else {
    		ArrayList<FileData> empty2 = new ArrayList<>();
    		return empty2;
    	}
    	
    }

    // TODO
    public ArrayList<FileData> findFilesInMultDir(String modifiedDate) {
    	ArrayList<FileData> givenDate = dateMap.get(modifiedDate);
    	ArrayList<FileData> returning = new ArrayList<>();
    	for (int n = 0; n < givenDate.size(); n++) {
    		String toFind = givenDate.get(n).name;
    		ArrayList<FileData> checking = this.findFilesByName(toFind);
    		if (checking.size() > 1) {
    			returning.add(givenDate.get(n));
    		}
    	}
    	return returning;
    }

    // TODO
    //try tomorrow
    public boolean removeByName(String name) {
    	if (nameMap.containsKey(name) == false) {
    		return false;
    	}
    	boolean toReturn1 = nameMap.remove(name);
    	boolean toReturn2 = false;
    	for (int i = dateMap.keys().size() - 1; i >= 0; i--) {
    		String keys = dateMap.keys().get(i);
    		ArrayList<FileData> toLoop = dateMap.get(keys);
    		for(int k = toLoop.size()-1; k >= 0; k--) {
    			if(toLoop.get(k).name.equals(name)) {
    				toLoop.remove(k);
    				toReturn2 = true;
    				
    			}
    		}
    		if (toLoop.size() == 0) {
    			dateMap.remove(keys);
    		}
    		
    	}
    	
    	return toReturn1 && toReturn2;
    }

    // TODO
    public boolean removeFile(String name, String directory) {
    	boolean return1 = false;
    	boolean return2 = false;
    	if (name == null || directory == null) {
    		return false;
    	}
    	if (nameMap.containsKey(name) == false) {
    		return false;
    	}
    	ArrayList<FileData> toLoop = nameMap.get(name);
    	ArrayList<String> string = new ArrayList<>();
    	int size1 = toLoop.size();
    	for (int i = 0; i < toLoop.size(); i++) {
    		if (toLoop.get(i).dir.equals(directory)) {
    			string.add(toLoop.get(i).lastModifiedDate);
    			toLoop.remove(i);
    			//return true;
    		}
    	}
    	//if all files from toLoop are removed, just remove the whole entry. 
    	if (toLoop.size() == 0) {
    		nameMap.remove(name);
    		return1 = true;
    	}
    	//if no files were removed, then make return1 false, if not then that means some files were removed so make return1 = true
    	else if (toLoop.size() != size1) {
    		return1 = true;
    	}
    	else {
    		return false;
    	}
    	if (string.size() == 0) {
    		return false;
    	}
    	for (int z = 0; z < string.size(); z++) {
    		ArrayList<FileData> toLoopIn = dateMap.get(string.get(z));
    		for (int x = 0; x < toLoopIn.size(); x++ ) {
        		if (toLoopIn.get(x).dir.equals(directory) && toLoopIn.get(x).name.equals(name)) {
        			toLoopIn.remove(x);
        		}
    		}
    		if (toLoopIn.size() == 0) {
    			dateMap.remove(string.get(z));
    			return2 = true;
    		}
    	}
    	boolean returned1 = return2 && return1;
    	return returned1;
    }

}
