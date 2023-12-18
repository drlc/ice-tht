import { useEffect, useState } from "react";

import genreApi from "../../services/genreApi";

function useGenres(name) {
  const [genres, setGenres] = useState([]);

  useEffect(() => {
    let call = async () => {
      let hasMorePages = true;
      let params = { page: 0, size: 500 };
      let possibleGenresNew = [];
      while (hasMorePages) {
        let res = await genreApi.list(params);
        possibleGenresNew = possibleGenresNew.concat(res.content);
        hasMorePages = res.isLast === false;
        if (hasMorePages) {
          params.page = params.page + 1;
        }
      }
      setGenres(possibleGenresNew);
    };
    call();
  }, []);


  return genres;
}

export default useGenres;