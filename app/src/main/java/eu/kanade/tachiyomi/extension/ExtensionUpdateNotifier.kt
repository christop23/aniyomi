package eu.kanade.tachiyomi.extension

import android.content.Context
import androidx.core.app.NotificationCompat
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.data.notification.NotificationReceiver
import eu.kanade.tachiyomi.data.notification.Notifications
import eu.kanade.tachiyomi.util.system.notification
import eu.kanade.tachiyomi.util.system.notificationManager

class ExtensionUpdateNotifier(private val context: Context) {

    fun promptUpdates(names: List<String>) {
        context.notificationManager.notify(
            Notifications.ID_UPDATES_TO_EXTS,
            context.notification(Notifications.CHANNEL_EXTENSIONS_UPDATE) {
                setContentTitle(
                    context.resources.getQuantityString(
                        R.plurals.update_check_notification_ext_updates,
                        names.size,
                        names.size,
                    ),
                )
                val extNames = names.joinToString(", ")
                setContentText(extNames)
                setStyle(NotificationCompat.BigTextStyle().bigText(extNames))
                setSmallIcon(R.drawable.ic_extension_24dp)
                setContentIntent(NotificationReceiver.openExtensionsPendingActivity(context))
                setAutoCancel(true)
            },
        )
    }
}
