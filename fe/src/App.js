import "./App.css";
import React, { useState } from "react";
import ListSongs from "./screens/list/ListSongs";
import Login from "./screens/login/Login";

function App() {
  const [activeView, setActiveView] = useState("list");
  const [loggedUser, setLoggedUser] = useState("c2a3fb6a-855a-4082-8037-e41dd1878711");
  // c2a3fb6a-855a-4082-8037-e41dd1878711

  let view = <Login confirmUserId={setLoggedUser} />;
  if (loggedUser) {
    switch (activeView) {
      case "list":
        view = <ListSongs goToView={setActiveView} userId={loggedUser} />;
        break;
      // case 'detail':
      //   return <Detail setActiveView={setActiveView} />
      // case 'create':
      //   return <Contact setActiveView={setActiveView} />
      default:
        view = "Loading...";
    }
  } 

  return (
    <div className="app">
      <header className="app-header">
        <h1>Music Library</h1>
        <h3>user: {loggedUser}</h3>
      </header>
      <main>{view}</main>
    </div>
  );
}

export default App;
