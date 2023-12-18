import "./ListSongs.css";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import { Box, TableSortLabel, IconButton } from "@mui/material";
import InfoIcon from "@mui/icons-material/Info";

import { visuallyHidden } from "@mui/utils";

import { useEffect, useState } from "react";
import songApi from "../../services/songApi";
import FilterSongForm from "../../components/organism/FilterSongForm";

const headers = [
  {
    id: "name",
    label: "Name",
  },
  {
    id: "year",
    label: "Year",
  },
  {
    id: "length",
    label: "Length (seconds)",
  },
];

export default function ListSongs({ goToView, userId }) {
  const [songs, setSongs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [page, setPage] = useState(0);
  const [noMorePage, setNoMorePage] = useState(false);
  const [showFilters, setShowFilters] = useState(false);
  const [filters, setFilters] = useState({});
  const [orderBy, setOrderBy] = useState(null);
  const [order, setOrder] = useState("asc");

  const filter = (body) => {
    const newFilters = {};
    for (let key in body) {
      if (body[key]) {
        newFilters[key] = body[key];
      }
    }
    setFilters(newFilters);
    setPage(0);
    setSongs([]);
    return Promise.resolve();
  };

  useEffect(() => {
    const loadSongs = (pageToGet) => {
      setLoading(true);
      songApi
        .list({ page: pageToGet, "user-id": userId, ...filters })
        .then((res) => {
          let allContent = [...songs, ...res.content];
          setSongs(allContent);
          setNoMorePage(res.last);
        })
        .finally(() => {
          setLoading(false);
        });
    };

    loadSongs(page);
  }, [page, userId, filters]); // eslint-disable-line react-hooks/exhaustive-deps

  let createSortHandler = (columnName) => {
    setOrderBy(columnName);
    let direction = order === "asc" ? "desc" : "asc";
    setOrder(direction);
    let newFilters = { ...filters };
    newFilters["sort"] = columnName + "," + direction;
    setFilters(newFilters);
    setPage(0);
    setSongs([]);
  };

  let tableView = songs.map((row) => (
    <TableRow
      key={row.name}
      sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
    >
      <TableCell component="th" scope="row">
        {row.name}
      </TableCell>
      <TableCell align="right">{row.year}</TableCell>
      <TableCell align="right">{row.length}</TableCell>
      <TableCell align="left">
        <IconButton onClick={() => goToView("detail", row.id)}>
          <InfoIcon />
        </IconButton>
      </TableCell>
    </TableRow>
  ));

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
      <Paper
        sx={{
          padding: "20px 18px",
          display: "flex",
          flexDirection: "column",
          gap: "10px",
        }}
      >
        <Button
          sx={{
            flexGrow: 1,
          }}
          variant="contained"
          onClick={() => setShowFilters(!showFilters)}
        >
          {showFilters ? "Hide" : "Show"} Filters
        </Button>
        {showFilters ? <FilterSongForm onSubmit={filter} /> : null}
      </Paper>
      <TableContainer component={Paper}>
        <Table aria-label="simple table">
          <TableHead>
            <TableRow>
              {headers.map((headCell) => (
                <TableCell
                  key={headCell.id}
                  align="left"
                  sortDirection={orderBy === headCell.id ? order : false}
                >
                  <TableSortLabel
                    active={orderBy === headCell.id}
                    direction={orderBy === headCell.id ? order : "asc"}
                    onClick={() => createSortHandler(headCell.id)}
                  >
                    {headCell.label}
                    {orderBy === headCell.id ? (
                      <Box component="span" sx={visuallyHidden}>
                        {order === "desc"
                          ? "sorted descending"
                          : "sorted ascending"}
                      </Box>
                    ) : null}
                  </TableSortLabel>
                </TableCell>
              ))}
              <TableCell align="right"></TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {tableView}
            {!noMorePage ? (
              <TableRow>
                <TableCell colSpan={5} align="center">
                  {loading ? (
                    "Loading..."
                  ) : (
                    <Button
                      variant="contained"
                      onClick={() => setPage(page + 1)}
                    >
                      More
                    </Button>
                  )}
                </TableCell>
              </TableRow>
            ) : null}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
}
