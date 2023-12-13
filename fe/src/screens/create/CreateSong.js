import "./CreateSong.css";

import { useEffect, useState } from "react";
import songApi from "../../services/songApi";
import artistApi from "../../services/artistApi";
import albumApi from "../../services/albumApi";
import {
  Paper,
  Button,
  TextField,
  Box,
  FormControl,
  Input,
  Autocomplete,
} from "@mui/material";

export default function CreateSong({ goToView, userId }) {
  const [possibleArtists, setPossibleArtists] = useState([]);
  const [searchedArtist, setSearchedArtist] = useState("");
  const [possibleGenres, setPossibleGenres] = useState([]);
  const [possibleAlbums, setPossibleAlbums] = useState([]);
  const [searchedAlbum, setSearchedAlbum] = useState("");
  const [isDisabled, setIsDisabled] = useState(true);
  const [isSending, setIsSending] = useState(false);

  const [songName, setSongName] = useState("");
  const [songYear, setSongYear] = useState(2023);
  const [songLength, setSongLength] = useState(0);
  const [artist, setArtist] = useState(null);
  const [genre, setGenre] = useState(null);
  const [album, setAlbum] = useState(null);

  useEffect(() => {
    setIsDisabled(
      !songName ||
        !songYear ||
        songYear <= 1900 ||
        !songLength ||
        songLength <= 0 ||
        !artist
    );
  }, [songName, songYear, songLength, artist]);

  useEffect(() => {
    let params = { page: 0 };
    if (searchedArtist) {
      params.name = searchedArtist;
    }
    artistApi
      .list(params)
      .then((res) => {
        setPossibleArtists(res.content);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [searchedArtist]);

  useEffect(() => {
    let params = { page: 0 };
    if (searchedAlbum) {
      params.name = searchedAlbum;
    }
    albumApi
      .list(params)
      .then((res) => {
        setPossibleAlbums(res.content);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [searchedAlbum]);

  let add = () => {
    if (isDisabled) {
      return;
    }
    let body = {
      name: songName,
      year: songYear,
      length: songLength,
      artistId: artist.id,
      userId: userId,
    };
    if (album) {
      body.albumId = album.id;
    }
    songApi
      .create(body)
      .then((res) => {
        goToView("list");
        setIsSending(true);
      })
      .finally(() => {
        setIsSending(false);
      });
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
        <FormControl>
          <TextField
            label="Name"
            required
            variant="standard"
            margin="normal"
            value={songName}
            onChange={(ev) => {
              setSongName(ev.target.value);
            }}
          />
        </FormControl>
        <FormControl>
          <Input
            placeholder="Year*"
            required={true}
            type="number"
            value={songYear}
            onChange={(ev) => setSongYear(ev.target.value)}
          />
        </FormControl>
        <FormControl>
          <Input
            placeholder="Length in seconds*"
            required={true}
            type="number"
            value={songLength}
            onChange={(ev) => {
              setSongLength(ev.target.value);
            }}
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
            renderInput={(params) => <TextField {...params} label="Artist*" />}
          />
        </FormControl>
        <FormControl>
          <Autocomplete
            freeSolo
            options={possibleAlbums.map((option) => {
              return { label: option.name, id: option.id };
            })}
            onInputChange={(ev, newValue) => {
              setSearchedAlbum(newValue);
            }}
            onChange={(ev, newValue) => {
              setAlbum(ev.target.value);
            }}
            renderInput={(params) => <TextField {...params} label="Album" />}
          />
        </FormControl>
        <FormControl>
          <Button variant="contained" disabled={isDisabled} onClick={add}>
            {isSending ? "Submitting..." : "Add"}
          </Button>
        </FormControl>
      </Box>
    </Paper>
  );
}
