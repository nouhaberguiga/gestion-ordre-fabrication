import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, RouterLinkActive],
  template: `
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3">
      <span class="navbar-brand fw-bold">⚙️ Fabrication App</span>
      <div class="navbar-nav ms-auto flex-row gap-3">
        <a class="nav-link" routerLink="/products"  routerLinkActive="text-warning fw-bold">Produits</a>
        <a class="nav-link" routerLink="/machines"  routerLinkActive="text-warning fw-bold">Machines</a>
        <a class="nav-link" routerLink="/employees" routerLinkActive="text-warning fw-bold">Employés</a>
        <a class="nav-link" routerLink="/orders"    routerLinkActive="text-warning fw-bold">Ordres</a>
      </div>
    </nav>
  `
})
export class NavbarComponent {}