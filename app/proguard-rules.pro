
            -keep class com.example.bmi.MainActivity { *; }
            # If your MainActivity extends AppCompatActivity or another common base class
            # you might also need rules for that, but often the above is sufficient
            # for the direct activity.
            # A more general rule to keep all activities that are in the manifest:
            -keep public class * extends android.app.Activity
            -keep public class * extends android.app.Application
            -keep public class * extends android.app.Service
            -keep public class * extends android.content.BroadcastReceiver
            -keep public class * extends android.content.ContentProvider
            -keep public class * extends android.app.backup.BackupAgentHelper
            -keep public class * extends android.preference.Preference
            -keep public class com.android.vending.licensing.ILicensingService