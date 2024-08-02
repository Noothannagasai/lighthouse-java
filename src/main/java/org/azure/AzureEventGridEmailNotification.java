//package org.azure;
//
//import com.azure.core.*;
//import com.azure.core.credential.TokenCredential;
//import com.azure.core.management.profile.AzureProfile;
//import com.azure.core.util.Context;
//import com.azure.messaging.eventgrid.EventGridEvent;
//import com.azure.messaging.eventgrid.EventGridEventConsumer;
//import com.azure.messaging.eventgrid.EventGridEventPublisherClient;
//import com.azure.messaging.eventgrid.EventGridEventPublisherClientBuilder;
//import com.azure.messaging.eventgrid.models.CloudEvent;
//import com.azure.messaging.eventgrid.models.EventGridEventInternal;
//import com.azure.messaging.eventgrid.models.EventGridEventSubscription;
//import com.azure.messaging.eventgrid.models.EventGridSystemEvent;
//import com.azure.messaging.eventgrid.models.EventGridSystemEventNames;
//import com.azure.messaging.eventgrid.models.EventGridSystemEventType;
//
//import java.util.function.Consumer;
//
//public class AzureEventGridEmailNotification {
//
//    public static void main(String[] args) {
//        // Set your Azure subscription credentials
//        String clientId = "YOUR_CLIENT_ID";
//        String clientSecret = "YOUR_CLIENT_SECRET";
//        String tenantId = "YOUR_TENANT_ID";
//        String storageAccountId = "YOUR_STORAGE_ACCOUNT_ID";
//
//        // Authenticate using Azure Identity
//        TokenCredential credential = new DefaultAzureCredentialBuilder()
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .tenantId(tenantId)
//                .build();
//
//        // Set up Event Grid client
//        EventGridEventPublisherClient eventGridClient = new EventGridEventPublisherClientBuilder()
//                .endpoint("YOUR_EVENT_GRID_ENDPOINT")
//                .credential(credential)
//                .serviceVersion(EventGridServiceVersion.V2021_10_01_PREVIEW)
//                .buildEventGridEventPublisherClient();
//
//        // Subscribe to events from the storage account
//        String eventSubscriptionId = "YOUR_EVENT_SUBSCRIPTION_ID";
//
//        EventGridEventConsumer eventGridConsumer = new EventGridEventConsumer();
//        eventGridConsumer.getEventGridEventPublisherClient().consumeEvents(eventSubscriptionId, 1,
//                Context.NONE, eventGridEventPagedFlux -> eventGridEventPagedFlux.subscribe(eventGridEvent -> {
//                    System.out.printf("Received event: %s%n", eventGridEvent.getData());
//                    handleEvent(eventGridEvent);
//                }));
//
//        System.out.println("Listening for events. Press Enter to exit.");
//        try {
//            System.in.read();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Handle incoming events
//    private static void handleEvent(EventGridEvent eventGridEvent) {
//        // Example: Check if the event is related to a storage blob created event
//        if (eventGridEvent.getEventType().equals(EventGridSystemEventType.STORAGE_BLOB_CREATED)) {
//            StorageBlobCreatedEventData eventData = eventGridEvent.getData().as(StorageBlobCreatedEventData.class);
//            String blobUrl = eventData.getUrl();
//            System.out.printf("Blob created: %s%n", blobUrl);
//
//            // Example: Validate if this event indicates an email notification trigger
//            // Implement your logic here to check for email notification trigger
//            if (isEmailNotificationTrigger(eventData)) {
//                System.out.println("Email notification triggered!");
//                // Perform actions like sending the email notification
//                sendEmailNotification();
//            } else {
//                System.out.println("Not an email notification trigger.");
//            }
//        } else {
//            System.out.println("Received an event of type: " + eventGridEvent.getEventType());
//        }
//    }
//
//    // Example method to check if the event indicates an email notification trigger
//    private static boolean isEmailNotificationTrigger(StorageBlobCreatedEventData eventData) {
//        // Implement your validation logic based on eventData properties
//        // For example, check blob properties or metadata to determine if email notification was intended
//        // Return true if it indicates an email notification, false otherwise
//        return eventData.getMetadata().containsKey("notificationType") && eventData.getMetadata().get("notificationType").equals("email");
//    }
//
//    // Example method to simulate sending email notification
//    private static void sendEmailNotification() {
//        System.out.println("Simulating sending email notification...");
//        // Implement email sending logic here
//    }
//}
