package com.helper;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class NotificationManager {
    private NotificationManager () {}

    private static NotificationManager notificationManager;

    public static NotificationManager getInstance() {
        if (notificationManager == null) {
            notificationManager = new NotificationManager();
        }
        return notificationManager;
    }

    public void success (String message) {
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Success");
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(1));
    }

    public void warning (String message) {
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Warning");
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndDismiss(Duration.millis(1000));
    }
}
