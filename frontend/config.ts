
function getAPI_URL():string{
  console.log("Running mode: " + import.meta.env.MODE)
  if (import.meta.env.MODE === 'test') return "http://backend:8080/api";
  else if (import.meta.env.MODE === 'deploy') return "http://143.129.39.49:8080/api";
  else if (import.meta.env.MODE === 'development') return "http://localhost:8080/api";
  else return "http://localhost:8080/api"; //default location
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
    {
      name: 'divider'
    },
    {
      name: 'Settings',
      location: '/settings',
      icon: 'mdi-cog-outline',
      allowedRoles: ['Any'],
    },
  ],
  MQTT:{
    REMOTE:"broker.hivemq.com",
    PORT:1883,
  },
  DATA_STATIC: false,
}

export default config
