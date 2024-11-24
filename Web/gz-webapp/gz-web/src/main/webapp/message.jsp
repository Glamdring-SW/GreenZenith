<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="src/stylesmessage.css">
<div id="messageService" class="message-service">
    <button id="messageToggle" class="message-button">
        <i class="bi bi-envelope-fill"></i>
    </button>
    <!-- 
        La idea con esta mensajeria es que cuando estes explorando los perfiles publicos y des en iniciar chat
         o cuando hagas click en el boton de mensaje en las tarjetas de compra, te dirija a este espacio para iniciar
         el chat, en el script viene mas o menos como pdorias manejarlo, espero que no te cueste tantisimo   :)
    -->
    <div id="messageWindow" class="message-window">
        <div class="message-header">
            <div id="backButton" class="back-button" style="display: none;">
                <i class="bi bi-chevron-left"></i>
            </div>
            <h5 id="windowTitle">Mensaje</h5>
            <div class="close-button" id="closeButton">
                <i class="bi bi-x"></i>
            </div>
        </div>

        <div class="message-content">
            <div id="contactsList" class="contacts-list">
            </div>

            <div id="messageThread" class="message-thread" style="display: none;">
                <div id="messageContainer" class="messages-container">
                    <!--The Messages will appear here-->
                </div>
                <div class="message-input">
                    <input type="text" id="messageInput" placeholder="Type a message...">
                    <button id="sendMessage">
                        <i class="bi bi-send-fill"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <script>
                document.addEventListener('DOMContentLoaded', function() {
            // Mock contacts data
            const contacts = [
                { id: 1, name: 'Jane Cooper', image: 'https://via.placeholder.com/40' },
                { id: 2, name: 'Wade Warren', image: 'https://via.placeholder.com/40' },
                { id: 3, name: 'Esther Howard', image: 'https://via.placeholder.com/40' },
                { id: 4, name: 'Cameron Williams', image: 'https://via.placeholder.com/40' }
            ];

            // Elements
            const messageToggle = document.getElementById('messageToggle');
            const messageWindow = document.getElementById('messageWindow');
            const contactsList = document.getElementById('contactsList');
            const messageThread = document.getElementById('messageThread');
            const backButton = document.getElementById('backButton');
            const closeButton = document.getElementById('closeButton');
            const windowTitle = document.getElementById('windowTitle');
            const messageInput = document.getElementById('messageInput');
            const sendMessage = document.getElementById('sendMessage');
            const messageContainer = document.getElementById('messageContainer');

            // State
            let currentContact = null;
            const messages = {};

            // Initialize contacts
            function initializeContacts() {
                contactsList.innerHTML = contacts.map(contact => `
                    <div class="contact-card" data-contact-id="${contact.id}">
                        <img src="${contact.image}" alt="${contact.name}" class="contact-image">
                        <span>${contact.name}</span>
                    </div>
                `).join('');

                // Add click handlers to contact cards
                document.querySelectorAll('.contact-card').forEach(card => {
                    card.addEventListener('click', () => {
                        const contactId = parseInt(card.getAttribute('data-contact-id'));
                        openMessageThread(contacts.find(c => c.id === contactId));
                    });
                });
            }

            // Toggle message window
            messageToggle.addEventListener('click', () => {
                messageWindow.style.display = messageWindow.style.display === 'none' ? 'flex' : 'none';
            });

            // Close window
            closeButton.addEventListener('click', () => {
                messageWindow.style.display = 'none';
            });

            // Back button
            backButton.addEventListener('click', () => {
                showContactsList();
            });

            // Open message thread
            function openMessageThread(contact) {
                currentContact = contact;
                contactsList.style.display = 'none';
                messageThread.style.display = 'flex';
                backButton.style.display = 'block';
                windowTitle.textContent = contact.name;

                // Initialize messages array for contact if it doesn't exist
                if (!messages[contact.id]) {
                    messages[contact.id] = [];
                }

                renderMessages();
            }

            // Show contacts list
            function showContactsList() {
                currentContact = null;
                contactsList.style.display = 'block';
                messageThread.style.display = 'none';
                backButton.style.display = 'none';
                windowTitle.textContent = 'Messages';
            }

            // Send message
            sendMessage.addEventListener('click', () => {
                if (!currentContact || !messageInput.value.trim()) return;

                const newMessage = {
                    text: messageInput.value.trim(),
                    sent: true,
                    timestamp: new Date()
                };

                messages[currentContact.id].push(newMessage);
                messageInput.value = '';
                renderMessages();

                // Simulate received message
                setTimeout(() => {
                    messages[currentContact.id].push({
                        text: 'This is a mock response',
                        sent: false,
                        timestamp: new Date()
                    });
                    renderMessages();
                }, 1000);
            });

            // Render messages
            function renderMessages() {
                if (!currentContact) return;

                messageContainer.innerHTML = messages[currentContact.id].map(message => `
                    <div class="message ${message.sent ? 'sent' : 'received'}">
                        ${message.text}
                    </div>
                `).join('');

                // Scroll to bottom
                messageContainer.scrollTop = messageContainer.scrollHeight;
            }

            // Enter key to send message
            messageInput.addEventListener('keypress', (e) => {
                if (e.key === 'Enter') {
                    sendMessage.click();
                }
            });

            // Initialize
            initializeContacts();
        });
    </script>
</div>
