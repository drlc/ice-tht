import "./ListSongs.css";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";

import { useEffect, useState } from "react";
import songApi from "../../services/songApi";

export default function ListSongs({ goToView, userId }) {
  const [songs, setSongs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [page, setPage] = useState(0);
  const [noMorePage, setNoMorePage] = useState(false);

  useEffect(() => {
    loadSongs();
  }, [page]);

  const loadSongs = () => {
    setLoading(true);
    songApi.list({ page: page, "user-id": userId }).then((res) => {
      setSongs(res.content);
      setNoMorePage(res.last);
      setLoading(false);
    });
  };

  let view = songs.map((row) => (
    <TableRow
      key={row.name}
      sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
    >
      <TableCell component="th" scope="row">
        {row.name}
      </TableCell>
      <TableCell align="right">{row.year}</TableCell>
      <TableCell align="right">{row.length}</TableCell>
      <TableCell align="left">{row.carbs}</TableCell>
    </TableRow>
  ));

  return (
    <div>
      <TableContainer component={Paper}>
        <Table aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>Song Name</TableCell>
              <TableCell align="right">
                Year
              </TableCell>
              <TableCell align="right">
                Length
              </TableCell>
              <TableCell align="right"></TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {view}
            {!noMorePage ? <TableRow>
              <TableCell colSpan={5} align="center">
                {loading ? (
                  "Loading..."
                ) : (
                  <Button variant="contained" onClick={setPage(page+1)}>More</Button>
                )}
              </TableCell>
            </TableRow> : null}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}
