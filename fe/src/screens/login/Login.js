import "./Login.css";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import FormControl from "@mui/material/FormControl";
import { useState } from "react";

export default function Login({ confirmUserId }) {
  const [userId, setUserId] = useState("");

  return (
    <div className="container">
      <Paper className="paper">
        <h1>Login</h1>
        <FormControl>
          <TextField id="outlined-basic" label="User Id" variant="outlined" onChange={(ev) => {
            setUserId(ev.target.value);
          }}/>
          <Button
            variant="contained"
            onClick={() => confirmUserId(userId)}
          >
            Login
          </Button>
        </FormControl>
      </Paper>
    </div>
  );
}
