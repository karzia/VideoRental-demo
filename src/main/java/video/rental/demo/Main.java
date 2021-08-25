package video.rental.demo;

import video.rental.demo.application.Interactor;
import video.rental.demo.domain.Repository;
import video.rental.demo.infrastructure.RepositoryMemImpl;
import video.rental.demo.presentation.CmdUI;
import video.rental.demo.util.SampleGenerator;

public class Main {
	private static CmdUI ui;

	public static void main(String[] args) {
		Repository repository = new RepositoryMemImpl();
		Interactor interactor = new Interactor(repository);
		new SampleGenerator(repository).generateSamples();
		ui = new CmdUI(interactor);
		ui.start();
	}
}
