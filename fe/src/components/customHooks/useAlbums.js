import { useEffect, useState } from "react";

import albumApi from "../../services/albumApi";

function useAlbums(name) {
  const [albums, setAlbums] = useState([]);

  useEffect(() => {
    let params = { page: 0 };
    if (name) {
      params.name = name;
    }
    albumApi
      .list(params)
      .then((res) => {
        setAlbums(res.content);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [name]);


  return albums;
}

export default useAlbums;