from django.contrib import admin
from .models import Person


class PersonAdmin(admin.ModelAdmin):
    fieldsets = [
        ('Person: ', {'fields': ['name', 'lastname', 'email', 'password']})
    ]
    list_display = ('name', 'lastname', 'email')
    list_filter = ['name', 'email']
    search_fields = ['name', 'email']


admin.site.register(Person, PersonAdmin)
