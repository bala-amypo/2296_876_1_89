@Override
public User updateUser(Long id, User user) {
    User existing = getUserById(id);

    existing.setEmail(user.getEmail());   // ✅ FIX
    existing.setPassword(user.getPassword());
    existing.setRole(user.getRole());

    return userRepository.save(existing);
}
