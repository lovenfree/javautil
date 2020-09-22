package test.graph.find;

import java.util.HashMap;
import java.util.LinkedList;

//Graph에 명시된 관계에 따라 데이타 뽑아오기

// 회사에 여러 프로젝트가 있는데 어떤 프로젝트들은 특정 프로젝트가 완료 되어야만진행할수 있는 프로젝트이다.
// 프로젝트의 목록과 각 프로젝트간 의존여부를 넘겨주면 의존도에 입각한 프로젝트의 진행순서를 
// 반환하는 함수를 구현하시
//node 와 node 간의 관계를 표시하는 것 Graph

//graph를 자료구조에 저장하는 방법 (Matrix, LinkedList)

//projects: a,b,c,d,e,f,g
//dependencies: (f,a),(f,b),(f,c),(b,a), (c,a) ,(a,e) ,(b,e), (d,g)

class Project{
	private String name;
	private LinkedList<Project> dependencies;
	private boolean marked;
	public Project(String name) {
		this.name = name;
		this.marked = false;
		this.dependencies = new LinkedList<Project>();
	}
	
	public void addDependency(Project project) {
		if(!dependencies.contains(project)) {
			dependencies.add(project);
		}
	}
	
	public LinkedList<Project> getDependencies(){
		return this.dependencies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}
}


class ProjectManager{
	private HashMap<String, Project> projects;
	public ProjectManager(String[] names,String[][] dependencies) {
		buildProjects(names);
		addDependencies(dependencies);
	}
	
	public void buildProjects(String[] names) {
		projects = new HashMap<String, Project>();
		for(int i=0;i<names.length;i++) {
			projects.put(names[i],new Project(names[i]));
		}
	}
	
	public void addDependencies(String[][] dependencies) {
		
		for (String[] dependency : dependencies) {
			Project before = findProject(dependency[0]);
			Project after = findProject(dependency[1]);
			after.addDependency(before);
		}		
	}
	
	private int index;
	public  Project[] buildOrder() {
			initMarkingFlages();
			Project[] ordered = new Project[this.projects.size()];
			index = 0;
			for(Project prj: this.projects.values()) {
				buildOrder(prj, ordered);
			}
			return ordered;
	}
	
	public void buildOrder(Project project, Project[] ordered) {
		if(!project.getDependencies().isEmpty()) {
			for(Project p: project.getDependencies()) {
				buildOrder(p, ordered);
			}
		}
		
		if(project.isMarked() == false) {
			project.setMarked(true);
			ordered[index] = project;
			index++;
			
		}
	}
	
	private void initMarkingFlages() {
		for(Project pro : this.projects.values()) {
			pro.setMarked(false);
		}
	}
	
	public Project findProject(String name) {
		return projects.get(name);
	}
}

public class FindData {
	public static void main(String[] args){
		String[] projects = {"a","b","c","d","e","f","g"};
		String[][] dependencied = {{"f","a"}, {"f","b"}, {"f","c"}, {"b","a"},
				{"c","a"}, {"a","e"}, {"b","e"}, {"d","g"}};
		
		ProjectManager pm = new ProjectManager(projects, dependencied);
		Project[] ps = pm.buildOrder();
		for(Project p:ps) {
			System.out.print(p.getName() + " ");
		}
	}

}
