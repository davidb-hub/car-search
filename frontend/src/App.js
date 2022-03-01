import React from 'react'

function App() {

  const url = "http://localhost:8080/car-service/sale-price/avg?";
  let searchParams = new URLSearchParams(url.search);

  const [carData, setCarData] = React.useState({});
  const [searchData, setSearchData] = React.useState({
    marke: "",
    modell: "",
    tillverkningsar: "",
    drivmedel: ""
  })

  function fetchData(searchUrl) {
    fetch(searchUrl)
      .then((res) => {
        if (res.ok) {
          return res.json()
        }
      })
      .then(data => setCarData(data))
  }

  function handleInput(event) {
    setSearchData(prev => {
      return {
        ...prev,
        [event.target.name]: event.target.value
      }
    })
  }

function handleSubmit(e) {
  e.preventDefault()
  searchParams.append('marke', searchData.marke)
  searchParams.append('modell', searchData.modell)
  searchParams.append('tillverkningsar', searchData.tillverkningsar)
  searchParams.append('drivmedel', searchData.drivmedel)
  let searchUrl = url + searchParams.toString();
  fetchData(searchUrl)
  console.log(searchUrl)
}

return (

  <div className="App">
    <form className="search-form" onSubmit={handleSubmit} id="search-form">
      <input type="text" placeholder='Märke...'
        required onChange={handleInput} name="marke" value={searchData.marke} />

      <input type="text" placeholder='Modell...'
        required onChange={handleInput} name="modell" value={searchData.modell} />

      <input type="text" placeholder='Tillverkningsår...'
        onChange={handleInput} name="tillverkningsar" value={searchData.tillverkningsar} />

      <input type="text" placeholder='Drivmedel...'
        onChange={handleInput} name="drivmedel" value={searchData.drivmedel} />

      <input type="submit" value="Sök" />
    </form>
    <br />
    <span>Matches: {carData.resultCount}</span>
    <br />
    <span>Average price: {carData.averagePrice}</span>
  </div>
);
}

export default App;
