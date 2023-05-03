
function getAPI_URL():string{
  console.log("Running mode: " + import.meta.env.MODE)
  if (import.meta.env.MODE === 'test') return "http://backend:8080/api/v3";
  else if (import.meta.env.MODE === 'deploy') return "http://143.129.39.49:8080/api/v3";
  else if (import.meta.env.MODE === 'development') return "http://localhost:8080/api/v3";
  else return "http://localhost:8080/api/v3"; //default location
}
const config = {
  //API_URL : 'http://localhost:8080/api/v1',
  API_URL : getAPI_URL(),
  NAVIGATION: [ // Describes the links in the navigation bar
    {
      name: 'Home',
      location: '/home',
      icon: 'mdi-home',
      allowedRoles: ['Any'],
    },
  ],
  DATA_STATIC: false,
}

export default config
