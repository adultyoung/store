// import SockJS from 'sockjs-client'
// import {Stomp} from '@stomp/stompjs'
//
//
// let stompClient = null;
// const handlers = [];
//
// export function connect() {
//     const socket = new SockJS('/gs-guide-websocket');
//     stompClient = Stomp.over(function () {
//         return socket;
//     });
//     stompClient.reconnect_delay = 5000;
//     stompClient.debug = () => {
//     };
//     stompClient.connect({}, frame => {
//         console.log('Connected: ' + frame);
//         stompClient.subscribe('/topic/activity', message => {
//             handlers.forEach(handler => handler(JSON.parse(message.body)))
//         })
//     })
// }
//
// export function addHandler(handler) {
//     handlers.push(handler)
// }
//
// export function disconnect() {
//     if (stompClient !== null) {
//         stompClient.disconnect()
//     }
//     console.log("Disconnected")
// }
//
// export function sendMessage(message) {
//     stompClient.send("/app/changeMessage", {}, JSON.stringify(message))
// }
