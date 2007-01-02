package nl.gridshore.samples.springosgi.test;

import java.util.List;

import nl.gridshore.samples.springosgi.BookReviewService;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.osgi.test.ConfigurableBundleCreatorTests;

public class BookReviewServiceSystemTest extends ConfigurableBundleCreatorTests {
	protected String getManifestLocation() { 
		return "classpath:nl/gridshore/samples/springosgi/test/MANIFEST.MF";
	}

	protected String[] getBundleLocations() {
		return new String[] {
			localMavenArtifact("org.springframework.osgi", "aopalliance.osgi","1.0-SNAPSHOT"),
			localMavenArtifact("org.springframework.osgi", "spring-context", "2.1-SNAPSHOT"),
			localMavenArtifact("org.springframework.osgi", "spring-beans","2.1-SNAPSHOT"),
			localMavenArtifact("org.springframework.osgi", "spring-osgi-core","1.0-SNAPSHOT"),
			localMavenArtifact("org.springframework.osgi", "spring-osgi-extender","1.0-SNAPSHOT"),
			localMavenArtifact("org.springframework.osgi", "spring-aop","2.1-SNAPSHOT"),
			localMavenArtifact("nl.gridshore.samples.springosgi", "bookreview-service","1.0-SNAPSHOT"),
		};
	}

	public void testOSGiStartedOk() {
		BundleContext bundleContext = getBundleContext();
		assertNotNull(bundleContext);
	}

	public void testSimpleServiceExported() {
		waitOnContextCreation("nl.gridshore.samples.springosgi.bookreviewservice");
		BundleContext context = getBundleContext();
        ServiceReference ref = context.getServiceReference(BookReviewService.class.getName());
        assertNotNull("Service Reference is null", ref);
        try {
            BookReviewService bookReviewService = (BookReviewService) context.getService(ref);
            assertNotNull("Cannot find the service", bookReviewService);
            List bookreviews = bookReviewService.findBookReviewByKeyword("habits"); 
            assertEquals(1, bookreviews.size());
        } finally {
            context.ungetService(ref);
        }
	}

}
