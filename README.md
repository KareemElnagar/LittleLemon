# Little Lemon Restaurant App

A modern Android app built with Jetpack Compose for the Little Lemon restaurant, featuring menu browsing, cart functionality, and user management.

## 🏗️ Architecture

This app follows a clean architecture pattern with the following structure:

### 📁 Package Structure

```
app/src/main/java/com/kareem/littlelemon/
├── data/                           # Data layer
│   ├── DataModels.kt              # Database and network models
│   └── repository/                 # Repository pattern
│       ├── MenuRepository.kt       # Menu data operations
│       └── CartRepository.kt       # Cart data operations
├── di/                            # Dependency Injection
│   └── DependencyInjection.kt     # DI setup
├── screens/                       # UI Screens
│   ├── Home.kt                    # Home screen
│   ├── MenuScreen.kt              # Menu grid screen
│   ├── DishDetails.kt             # Dish details screen
│   ├── Orders.kt                  # Cart/Orders screen
│   ├── Profile.kt                 # User profile screen
│   └── Onboarding.kt              # Onboarding screen
├── ui/                           # UI Components
│   ├── components/                # Reusable UI components
│   │   ├── MenuItemCard.kt        # Menu item card component
│   │   └── CartItemCard.kt        # Cart item card component
│   └── theme/                     # App theming
├── util/                         # Utilities
│   ├── AppConstants.kt            # App constants
│   ├── Constants.kt               # Legacy constants
│   ├── Destinations.kt            # Navigation destinations
│   └── LittleLemonApplication.kt  # Application class
├── viewmodel/                    # ViewModels
│   └── MenuViewModel.kt           # Main ViewModel
├── MainActivity.kt               # Main activity
├── BottomNavigation.kt           # Bottom navigation
├── NavigationComposable.kt       # Navigation setup
├── Network.kt                    # Network models (legacy)
└── Database.kt                   # Database models (legacy)
```

## 🚀 Key Features

### ✅ Implemented Features
- **Menu Browsing**: Browse restaurant menu with categories
- **Search Functionality**: Search menu items by name
- **Cart Management**: Add/remove items with quantity control
- **Dish Details**: Detailed view of menu items
- **User Onboarding**: Registration and profile management
- **Responsive UI**: Modern Material Design 3 interface

### 🔧 Technical Features
- **Repository Pattern**: Clean separation of data logic
- **Dependency Injection**: Centralized dependency management
- **State Management**: Reactive state with StateFlow
- **Room Database**: Local data persistence
- **Ktor Networking**: Modern HTTP client
- **Jetpack Compose**: Modern UI toolkit

## 🛠️ Architecture Benefits

### 1. **Separation of Concerns**
- **Data Layer**: Handles all data operations
- **UI Layer**: Pure presentation logic
- **Business Logic**: Centralized in ViewModels

### 2. **Testability**
- Repository pattern enables easy unit testing
- Dependency injection allows mocking
- Clear interfaces for testing

### 3. **Maintainability**
- Modular structure
- Reusable components
- Clear naming conventions

### 4. **Scalability**
- Easy to add new features
- Consistent patterns
- Extensible architecture

## 📱 Screens

### Home Screen
- Restaurant information
- Search functionality
- Category filtering
- Menu item browsing

### Menu Screen
- Grid layout of menu items
- Click to view details

### Dish Details Screen
- Detailed item information
- Add to cart functionality
- Image display

### Cart/Orders Screen
- Cart items with quantities
- Quantity controls
- Total calculation
- Remove items

### Profile Screen
- User information
- Settings management

## 🔄 Data Flow

```
UI (Compose) → ViewModel → Repository → Data Source (Database/Network)
     ↑                                                      ↓
     ←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←←
```

## 🎯 Best Practices Implemented

1. **Repository Pattern**: Clean data access
2. **Dependency Injection**: Centralized dependencies
3. **StateFlow**: Reactive state management
4. **Composable Components**: Reusable UI
5. **Constants Management**: Centralized constants
6. **Error Handling**: Proper exception handling
7. **Navigation**: Type-safe navigation

## 🚀 Getting Started

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the app

## 📦 Dependencies

- **Jetpack Compose**: Modern UI toolkit
- **Room**: Database persistence
- **Ktor**: HTTP networking
- **Glide**: Image loading
- **Material Design 3**: Modern UI components

## 🔮 Future Improvements

- [ ] Add unit tests
- [ ] Implement checkout flow
- [ ] Add payment integration
- [ ] Push notifications
- [ ] Offline support
- [ ] User preferences
- [ ] Order history
- [ ] Favorites system

## 📄 License

This project is for educational purposes. 