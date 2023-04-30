package com.seminolestate.edu;

import java.util.ArrayList;
import java.util.List;

class GraphNode {
    int id;
    List<GraphNode> children = new ArrayList<>();
    boolean isExit = false;

    public GraphNode(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ((Integer) id).toString();
    }
}

class AnimalTree {
    public static void main(String[] args) {
        List<GraphNode> nodes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            nodes.add(new GraphNode(i + 1));
        }
        nodes.get(19).isExit = true;
        nodes.get(15).isExit = true;
        nodes.get(12).isExit = true;
        connect(nodes, 1, 2);
        connect(nodes, 2, 3);
        connect(nodes, 3, 4);
        connect(nodes, 4, 5);
        connect(nodes, 1, 6);
        connect(nodes, 3, 8);
        connect(nodes, 4, 9);
        connect(nodes, 7, 8);
        connect(nodes, 6, 11);
        connect(nodes, 7, 12);
        connect(nodes, 9, 14);
        connect(nodes, 10, 15);
        connect(nodes, 11, 12);
        connect(nodes, 14, 15);
        connect(nodes, 13, 18);
        connect(nodes, 15, 20);
        connect(nodes, 16, 17);
        connect(nodes, 17, 18);
        connect(nodes, 18, 19);
        connect(nodes, 19, 20);

        for (GraphNode exitNode : nodes) {
            if (exitNode.isExit) {
                System.out.println("Exit node " + exitNode.id + ":");
                List<GraphNode> visited = new ArrayList<>();
                List<List<GraphNode>> paths = new ArrayList<>();
                solve(nodes.get(2), exitNode, visited, new ArrayList<>(), paths);
                if (!paths.isEmpty()) {
                    for (List<GraphNode> path : paths) {
                        for (int i = 0; i < path.size() - 1; i++) {
                            System.out.print(path.get(i).id + " -> ");
                        }
                        System.out.println(path.get(path.size() - 1).id);
                    }
                } else {
                    System.out.println("No path found");
                }
            }
        }
    }

    private static void solve(GraphNode node, GraphNode exitNode, List<GraphNode> visited, List<GraphNode> currentPath, List<List<GraphNode>> paths) {
        visited.add(node);
        currentPath.add(node);

        if (node == exitNode) {
            List<GraphNode> path = new ArrayList<>(currentPath);
            paths.add(path);
        } else {
            for (GraphNode child : node.children) {
                if (!visited.contains(child)) {
                    solve(child, exitNode, visited, currentPath, paths);
                }
            }
        }

        visited.remove(node);
        currentPath.remove(currentPath.size() - 1);
    }

    private static void connect(List<GraphNode> nodes, int nodeId1, int nodeId2) {
        nodes.get(nodeId1 - 1).children.add(nodes.get(nodeId2 - 1));
        nodes.get(nodeId2 - 1).children.add(nodes.get(nodeId1 - 1));
    }
}



/*

	public static void main2(String[] args) {

		DepartmentNode node1 = new DepartmentNode("Bonneville Power Administration", 150000, 21);
		DepartmentNode node2 = new DepartmentNode("Southeastern Power Administration", 190000, 11);
		DepartmentNode node3 = new DepartmentNode("Southwestern Power Administration", 110000, 15);
		DepartmentNode node4 = new DepartmentNode("Western Area Power Administration", 120000, 14);

		DepartmentNode node5 = new DepartmentNode("Assistant Secretary for Electricity", 191000, 12, node1, node2,
				node3, node4);

		DepartmentNode node6 = new DepartmentNode("Assistant Secretary for Fossil Energy", 100000, 10);
		DepartmentNode node7 = new DepartmentNode("Assistant Secretary for Nuclear Energy", 100000, 10);
		DepartmentNode node8 = new DepartmentNode("Assistant Secretary for Energy Efficiency and Renewable Energy",
				100000, 10);

		DepartmentNode node9 = new DepartmentNode("Office of Undersecretary of Energy", 100000, 10, node5, node6, node7,
				node8);

		DepartmentNode node10 = new DepartmentNode("Office of Science", 110000, 15);
		DepartmentNode node11 = new DepartmentNode("Office of AI and Tech", 120000, 14);

		DepartmentNode node12 = new DepartmentNode("Office of Undersecretary for Science", 191000, 12, node10, node11);

		DepartmentNode node13 = new DepartmentNode("Chief of Staff", 50000, 1);
		DepartmentNode node14 = new DepartmentNode("Ombudsman", 50000, 1);

		DepartmentNode root = new DepartmentNode("Office of the Secretary", 191000, 12, node12, node9, node13, node14);

		root.printSubtree(0);
		System.out.println("Total budget is " + totalBudget(root));
		System.out.printf("Average number of employees per : %.2f", root.averageNumOfEmployeesPerNode());
		System.out.println("\nThe Tree has " + root.countNodes() + " nodes.");
	}

	public static int totalBudget(DepartmentNode node) {
		int answer = node.budget;
		for (DepartmentNode child : node.children) {
			answer += totalBudget(child);
		}
		return answer;
	}

}

class DepartmentNode {
	String name;
	int budget;
	int numOfEmployees;
	List<DepartmentNode> children = new ArrayList<>();

	public DepartmentNode(String name, int budget, int numOfEmployees, DepartmentNode... children) {
		this.name = name;
		this.budget = budget;
		this.numOfEmployees = numOfEmployees;
		for (DepartmentNode child : children) {
			this.children.add(child);
			this.numOfEmployees += child.numOfEmployees;
		}
	}

	public int countNodes() {
		int count = 1;
		for (DepartmentNode child : children) {
			count += child.countNodes();
		}
		return count;
	}

	public double averageNumOfEmployeesPerNode() {
		return (double) totalNumOfEmployees() / countNodes();
	}

	public int totalNumOfEmployees() {
		int total = 0;
		Stack<DepartmentNode> stack = new Stack<>();
		stack.push(this);
		while (!stack.isEmpty()) {
			DepartmentNode node = stack.pop();
			total += node.numOfEmployees;
			for (DepartmentNode child : node.children) {
				stack.push(child);
			}
		}
		return total;
	}

	public void printSubtree(int level) {
		for (int i = 0; i < level; i++) {
			System.out.print(" ");
		}
		System.out.println(name + " - Employees: " + numOfEmployees + ", Budget: " + budget);

		for (DepartmentNode child : children) {
			child.printSubtree(level + 1);
		}
	}
}
*/
