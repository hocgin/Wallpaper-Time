package in.hocg.wallpaper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vansuita.materialabout.builder.AboutBuilder;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		View view = AboutBuilder.with(this)
				.setName("hocgin")
				.setPhoto(R.mipmap.profile)
				.setCover(R.mipmap.profile_cover)
				.setSubTitle("https://hocg.in")
				.setBrief("I'm warmed of mobile technologies. Ideas maker, curious and nature lover.")

				.setAppIcon(R.mipmap.app_icon)
				.setAppName(R.string.app_name)
				.addGitHubLink("hocgin")
				.addWebsiteLink("https://hocg.in")
				.addEmailLink("hocgin@gmail.com")
				.setVersionNameAsAppSubTitle()
				.setWrapScrollView(true)
				.setLinksAnimated(true)
				.build();

		setContentView(view);
		getSupportActionBar().hide();
	}
}
