import { useEffect, useState } from "react";

import artistApi from "../../services/artistApi";

function useArtists(name) {
  const [artists, setArtists] = useState([]);

  useEffect(() => {
    let params = { page: 0 };
    if (name) {
      params.name = name;
    }
    artistApi
      .list(params)
      .then((res) => {
        setArtists(res.content);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [name]);


  return artists;
}

export default useArtists;