package tasksquire;

enum TaskStatus {
	NOT_DONE("Not Done"), NOT_CONFIRMED("Not Confirmed"), DONE("Done"), MISSED("Missed")
	TaskStatus(String label) {this.label = label}
	String label
	String getName() {name()}
}