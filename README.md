# Desafio Python Django + Android

## 1. Aplicação Python
A funcionalidades desse sistemas são: criar, editar e deletar uma pessoa e listar todas as pessoas do banco.
A url principal da aplicação inicia em uma lista de pessoas cadastradas no banco, possuindo as opções de cadastrar uma nova, editar e/ou exlcluir uma pessoa da lista.

### 1.1 Instalando Requisitos
- Tenha instalado na máquina o [python 3.8](https://www.python.org/downloads/release/python-386/)
- Crie uma venv na pasta do projeto python 
```python -m venv nomevenv ```
- Inicie a venv com o script "activate" localizado em: venvName/Scripts/activate 
- Instale as libs do requirements 
```pip install -r requirements.txt ```

### 1.2 Migrate banco de dados
- No arquivo myproject/settings.py altere os campos **NAME, USER, PASSWORD** 
(HOST e PORT caso não esteja usando os valores default do postgres) da propriedade **DATABASES** para o seu banco local ex:
``` 
DATABASES = {
        'default': {
        'ENGINE': 'django.db.backends.postgresql_psycopg2',
        'NAME': 'nome_do_banco',
        'USER': 'usuario',
        'PASSWORD': 'senha',
        'HOST': '127.0.0.1',
        'PORT': '',
    }
}
```
- Na pasta do projeto execute os comandos na seguinte ordem:
```
python manage.py migrate
python manage.py makemigrations application
```

### 1.3 Admin Django
- Para acessar a página do admin crie um super usuario com o comando ``` python manage.py createsuperuser ```. Após a criação, poderá executar o login na pagina administradora pela url *127.0.0.1:8000/admin*

### 1.4 Executando Aplicação
- Com o venv ativado execute o comando ```python manage.py runserver``` na pasta do projeto

## 2. Aplicação Android

Uma aplicação Android que faz um login request ao Django.

