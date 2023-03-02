import logo from './logo.svg';
import './App.css';
import React, { useState, useEffect } from 'react';
import axios from 'axios';


function App() {
  const [clients, setClient] = useState([]);

  useEffect(() => {
    axios.get('/api/hello')
        .then(response => {
          setClient(response.data);
        })
        .catch(error => {
          console.log(error);
        });
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <h1>Client List</h1>
        <ul>
          {clients.map(client => (
              <li key={client.id}>{client.name} by {client.email}</li>
          ))}
        </ul>

      </header>
    </div>
  );
}

export default App;
