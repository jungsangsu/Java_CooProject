package FunctionTest.PDF;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.common.MyAnnotationCallback;

import javax.swing.*;

public class PDFJframeViewer {
	public static void main(String[] args) {
		// Get a file from the command line to open
		String filePath = "C:\\eclipse\\aa.pdf";

		// build a component controller
		SwingController controller = new SwingController();

		SwingViewBuilder factory = new SwingViewBuilder(controller);

		JPanel viewerComponentPanel = factory.buildViewerPanel();

		// add interactive mouse link annotation support via callback
		controller.getDocumentViewController()
				.setAnnotationCallback(new MyAnnotationCallback(controller.getDocumentViewController()));

		JFrame applicationFrame = new JFrame();
		applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		applicationFrame.getContentPane().add(viewerComponentPanel);

		// Now that the GUI is all in place, we can try openning a PDF
		controller.openDocument(filePath);

		// show the component
		applicationFrame.pack();
		applicationFrame.setVisible(true);
	}
}
