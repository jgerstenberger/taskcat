package taskcat;

enum TaskStatus {
	NOT_DONE("Not Done"), DONE("Done"), MISSED("Missed")
	TaskStatus(String label) {this.label = label}
	String label
	String getName() {name()}
}