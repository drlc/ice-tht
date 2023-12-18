import { useState } from "react";

import useArtists from "../customHooks/useArtists";
import {
  Button,
  TextField,
  Box,
  FormControl,
  Input,
  Autocomplete,
} from "@mui/material";

export default function FilterSongForm({ onSubmit }) {
  const [searchedArtist, setSearchedArtist] = useState("");
  const possibleArtists = useArtists(searchedArtist);
  const [isSending, setIsSending] = useState(false);

  const [songYear, setSongYear] = useState(null);
  const [artist, setArtist] = useState(null);

  let action = () => {
    let body = {};
    if (songYear) {
      body["year"] = songYear;
    }
    if (artist) {
      body["artist-name"] = artist.label;
    }
    setIsSending(true);
    onSubmit(body).finally(() => {
      setIsSending(false);
    });
  };

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "space-between",
        width: "100%",
        gap: "10px",
        margin: "auto",
      }}
    >
      <FormControl>
        <Input
          placeholder="Year"
          type="number"
          value={songYear}
          onChange={(ev) => setSongYear(ev.target.value)}
        />
      </FormControl>
      <FormControl>
        <Autocomplete
          freeSolo
          options={possibleArtists.map((option) => {
            return { label: option.name, id: option.id };
          })}
          onInputChange={(ev, newValue) => {
            setSearchedArtist(newValue);
          }}
          onChange={(ev, newValue) => {
            setArtist(newValue);
          }}
          renderInput={(params) => <TextField {...params} label="Artist" />}
        />
      </FormControl>
      <FormControl>
        <Box
          sx={{
            display: "flex",
            flexDirection: "row",
            width: "100%",
            gap: "10px",
            margin: "auto",
          }}
        >
          <Button
            sx={{ flexGrow: 1 }}
            variant="contained"
            color="success"
            onClick={action}
          >
            {isSending ? "Submitting..." : "Submit"}
          </Button>
        </Box>
      </FormControl>
    </Box>
  );
}
