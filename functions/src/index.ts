import * as functions from 'firebase-functions';
import * as admin from 'firebase-admin'  
admin.initializeApp(functions.config().firebase)
const fcm = admin.messaging();

export const listener = functions.database.ref('posts/{postId}')
    .onCreate(async event => {
        const payload : admin.messaging.MessagingPayload = {
            notification : {
                title : 'Ultima Hora',
                body : 'Algo esta sucediendo'
            }
        }
        return fcm.sendToTopic('general', payload)
    })
