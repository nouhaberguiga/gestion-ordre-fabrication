# Gestion ordre fabrication

## Lancer avec Docker Desktop

Prerequis:

- Docker Desktop demarre
- Ports libres sur la machine: `80`, `8080`, `3308`

Demarrage:

```powershell
docker compose up --build
```

Acces:

- Frontend Angular: http://localhost
- Backend Spring Boot: http://localhost:8080
- MySQL depuis la machine: `localhost:3308`

Identifiants MySQL:

- Base: `bigl`
- Utilisateur: `root`
- Mot de passe: `root`

Arret:

```powershell
docker compose down
```

Arret avec suppression des donnees MySQL:

```powershell
docker compose down -v
```
