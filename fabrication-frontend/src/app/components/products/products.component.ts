import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/models';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './products.component.html'
})
export class ProductsComponent implements OnInit {

  products: Product[] = [];
  msg = '';
  msgType = 'success';
  showForm = false;
  editing = false;

  form: Product = { name: '', type: '', reference: '', price: 0, quantityStock: 0, supplier: '' };
  editId: number | null = null;

  constructor(private service: ProductService) {}

  ngOnInit() { this.load(); }

  load() {
    this.service.getAll().subscribe({
      next: data => this.products = data,
      error: () => this.alert('Erreur de chargement', 'danger')
    });
  }

  openCreate() {
    this.form = { name: '', type: '', reference: '', price: 0, quantityStock: 0, supplier: '' };
    this.editing = false;
    this.editId = null;
    this.showForm = true;
  }

  openEdit(p: Product) {
    this.form = { ...p };
    this.editId = p.id!;
    this.editing = true;
    this.showForm = true;
  }

  save() {
    if (!this.form.name || !this.form.type || !this.form.reference || !this.form.supplier) {
      return this.alert('Tous les champs sont obligatoires', 'danger');
    }
    if (this.form.price <= 0) return this.alert('Le prix doit être > 0', 'danger');

    const obs = this.editing
      ? this.service.update(this.editId!, this.form)
      : this.service.create(this.form);

    obs.subscribe({
      next: () => { this.alert(this.editing ? 'Produit modifié' : 'Produit créé'); this.showForm = false; this.load(); },
      error: err => this.alert(err.error?.error || 'Erreur', 'danger')
    });
  }

  delete(id: number) {
    if (!confirm('Supprimer ce produit ?')) return;
    this.service.delete(id).subscribe({
      next: () => { this.alert('Produit supprimé'); this.load(); },
      error: err => this.alert(err.error?.error || 'Erreur suppression', 'danger')
    });
  }

  alert(text: string, type = 'success') {
    this.msg = text; this.msgType = type;
    setTimeout(() => this.msg = '', 3000);
  }
}