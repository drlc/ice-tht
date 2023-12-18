import "./DetailSong.css";

import { Paper, Box } from "@mui/material";
import UpsertSongForm from "../../components/organism/UpsertSongForm";

export default function CreateSong({ goToView, songId, userId }) {

  return (
    <Paper className="paper">
      <h4>Detail</h4>
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
        <UpsertSongForm onSubmit={null} userId={userId} songId={songId} />
      </Box>
    </Paper>
  );
}
