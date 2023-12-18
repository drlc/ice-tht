import { useEffect, useState } from "react";

import songApi from "../../services/songApi";
import useArtists from "../customHooks/useArtists";
import useAlbums from "../customHooks/useAlbums";
import useGenres from "../customHooks/useGenres";
import {
  Button,
  TextField,
  Box,
  FormControl,
  Input,
  Autocomplete,
  Select,
  OutlinedInput,
  Chip,
  MenuItem,
} from "@mui/material";

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};

export default function UpsertSongForm({ onSubmit, userId, songId }) {
  const [searchedArtist, setSearchedArtist] = useState("");
  const possibleArtists = useArtists(searchedArtist);
  const possibleGenres = useGenres();
  const [searchedAlbum, setSearchedAlbum] = useState("");
  const possibleAlbums = useAlbums(searchedAlbum);
  const [isDisabled, setIsDisabled] = useState(true);
  const [isSending, setIsSending] = useState(false);

  const [songName, setSongName] = useState("");
  const [songYear, setSongYear] = useState(2023);
  const [songLength, setSongLength] = useState(0);
  const [artist, setArtist] = useState(null);
  const [genres, setGenres] = useState([]);
  const [album, setAlbum] = useState(null);

  const readOnly = !onSubmit && songId ? true : false;

  useEffect(() => {
    setIsDisabled(
      !songName ||
        !songYear ||
        songYear <= 0 ||
        !songLength ||
        songLength <= 0 ||
        !artist
    );
  }, [songName, songYear, songLength, artist]);

  useEffect(() => {
    if (songId) {
      songApi.get(songId).then((res) => {
        setSongName(res.name);
        setSongYear(res.year);
        setSongLength(res.length);
        setGenres(res.genreIds);
        setSearchedAlbum(res.albumName);
        setSearchedArtist(res.artistName);
        setArtist({ id: res.artistId, label: res.artistName });
        if (res.albumId){
          setAlbum({ id: res.albumId, label: res.albumName });
        }
        
      });
    }
  }, [songId]);

  let action = () => {
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
    if (genres.length > 0) {
      body.genreIds = genres;
    }
    setIsSending(true);
    songApi
      .create(body)
      .then(() => {
        onSubmit(body);
      })
      .finally(() => {
        setIsSending(false);
      });
  };

  const handleGenreChange = (event) => {
    const {
      target: { value },
    } = event;
    if (genres.find((genre) => genre === value.id)) {
      let newGenres = [...genres];
      newGenres.splice(newGenres.indexOf(value.id), 1);
      setGenres([...newGenres]);
    } else {
      setGenres([...genres, value.id]);
    }
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
          placeholder="Year"
          required
          type="number"
          value={songYear}
          onChange={(ev) => setSongYear(ev.target.value)}
        />
      </FormControl>
      <FormControl>
        <Input
          placeholder="Length in seconds"
          required
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
          value={artist}
          renderInput={(params) => <TextField {...params} label="Artist" />}
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
            setAlbum(newValue);
          }}
          value={album}
          renderInput={(params) => <TextField {...params} label="Album" />}
        />
      </FormControl>
      <FormControl>
        <Select
          onChange={handleGenreChange}
          value={genres}
          input={<OutlinedInput />}
          renderValue={(selected) => {
            return (
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: 0.5 }}>
                {selected.map((value) => {
                  let itemGenre = possibleGenres.find(
                    (genre) => genre.id === value
                  );
                  return <Chip key={itemGenre.id} label={itemGenre.name} />;
                })}
              </Box>
            );
          }}
          MenuProps={MenuProps}
        >
          {possibleGenres.map((genre) => (
            <MenuItem key={genre.id} value={genre}>
              {genre.name}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      {readOnly ? null : (
        <FormControl>
          <Button variant="contained" disabled={isDisabled} onClick={action}>
            {isSending ? "Submitting..." : "Submit"}
          </Button>
        </FormControl>
      )}
    </Box>
  );
}
