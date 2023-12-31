import "./App.css";
import React, { useState } from "react";
import ListSongs from "./screens/list/ListSongs";
import CreateSong from "./screens/create/CreateSong";
import DetailSong from "./screens/detail/DetailSong";
import Login from "./screens/login/Login";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";

function App() {
  const [activeView, setActiveView] = useState("list");
  const [loggedUser, setLoggedUser] = useState("");
  const [songId, setSongId] = useState(null);

  let view = <Login confirmUserId={setLoggedUser} />;
  if (loggedUser) {
    switch (activeView) {
      case "list":
        view = (
          <ListSongs
            goToView={(stateName, songId) => {
              setActiveView(stateName);
              if (songId) {
                setSongId(songId);
              }
            }}
            userId={loggedUser}
          />
        );
        break;
      case "detail":
        view = (
          <DetailSong
            setActiveView={setActiveView}
            songId={songId}
            userId={loggedUser}
          />
        );
        break;
      case "create":
        view = <CreateSong goToView={setActiveView} userId={loggedUser} />;
        break;
      default:
        view = "Loading...";
    }
  }

  return (
    <div className="app">
      <header className="app-header">
        <h1>Music Library</h1>
        <Box sx={{ display: "flex", justifyContent: "space-between" }}>
          <h3>user: {loggedUser}</h3>
          {loggedUser && (
            <Button
              variant="contained"
              margin="normal"
              onClick={() =>
                setActiveView(activeView !== "list" ? "list" : "create")
              }
            >
              {activeView !== "list" ? "Song List" : "Add new Song"}
            </Button>
          )}
        </Box>
      </header>
      <main>{view}</main>
    </div>
  );
}

export default App;
