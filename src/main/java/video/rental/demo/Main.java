package video.rental.demo;

import video.rental.demo.application.Interactor;
import video.rental.demo.domain.IReport;
import video.rental.demo.domain.Repository;
import video.rental.demo.domain.TextReportImpl;
import video.rental.demo.infrastructure.RepositoryMemImpl;
import video.rental.demo.presentation.CmdUI;
import video.rental.demo.presentation.GraphicUI;
import video.rental.demo.presentation.UIInterface;
import video.rental.demo.util.SampleGenerator;

public class Main {
	private static UIInterface ui;

	public static void main(String[] args) {

		IReport report = new TextReportImpl();
		Repository repository = new RepositoryMemImpl();
		Interactor interactor = new Interactor(repository, report);
		new SampleGenerator(repository, report).generateSamples();
		//ui = new CmdUI(interactor);
		ui = new GraphicUI(interactor);
		ui.start();
	}
}
