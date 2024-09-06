<br/>
<div align="center">
<h3 align="center">🎉 Bate Papo</h3>
<p align="center">
A simple chat server using Netcat, built for fun and learning! 💻
</p>
</div>

## 🚀 About The Project

This project was assigned during the 7th week of my **Bootcamp** at Code for All_. The goal was to create a chat server from scratch.  
During this week, we covered topics like networking, threading, sockets, and concurrency. The project needed to integrate all of these concepts.  

### 🏆 Achievement

This chat server project was developed in 24 hours during my bootcamp at **Code For All_**. The challenge was to build a network application, and I chose to create this chat server to apply what we had just learned about threads, sockets, and concurrency. 

### 🛠️ Project Overview

#### 🎯 Objective

Build a robust chat server that supports multiple clients using Netcat. The server handles communication between clients and implements basic chatroom features such as private messaging, changing nicknames, and listing connected users.

### 🌟 Features

Here are some key features of the chat server:

- **Multiple Client Connections**: Connect multiple users at once using Netcat (`nc`).
- **Commands Support**:
  - `/list`: Lists all connected users.
  - `/nickname [newName]`: Changes the nickname of the client.
  - `/whisper [user] [message]`: Sends a private message to a specific user.
  - `/exit`: Disconnects the client from the server.

### 🧩 Technologies Used

- **Backend**: Java
- **Client**: NetCat
- **Concurrency**: Threads and Sockets

### 💡 Inspiration

I was inspired by the need for fast, scalable network solutions. A chat server is a classic way to showcase these concepts, and I wanted to challenge myself by adding extra features like private messaging and client management with nicknames.

### 🎉 Acknowledgements

- **Code For All_ Bootcamp**: For the challenge and support.
- **NetCat**: For its simplicity in testing network applications.

### 📈 Future Enhancements

- **GUI Client**: Develop a graphical user interface (GUI) to replace the current command-line Netcat client.
- **Authentication System**: Implement a login system with password encryption.
- **Persistent Chat Logs**: Store chat history on the server for later retrieval.
- **Direct File Transfers**: Allow users to send files through the chat server.

### 📜 License

This project is open-source and available under the MIT License.
