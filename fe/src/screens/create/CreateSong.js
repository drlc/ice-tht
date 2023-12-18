import "./CreateSong.css";

import { Paper, Box } from "@mui/material";
import UpsertSongForm from "../../components/organism/UpsertSongForm";

export default function CreateSong({ goToView, userId }) {
  let onSubmit = (body) => {
    goToView("list");
  };

  return (
    <Paper className="paper">
      <h4>Add new song</h4>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "space-between",
          width: "60%",
          gap: "10px",
          margin: "auto",
        }}
      >
        <UpsertSongForm
          onSubmit={onSubmit}
          userId={userId}
        />
      </Box>
    </Paper>
  );
}
